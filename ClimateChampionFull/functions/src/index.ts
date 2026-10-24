import * as functions from "firebase-functions";
import * as admin from "firebase-admin";
import * as express from "express";
import * as cors from "cors";

admin.initializeApp();
const db = admin.firestore();

const app = express();
app.use(cors({ origin: true }));
app.use(express.json());

// ─── Auth Middleware ──────────────────────────────────────────────────────────
const authenticate = async (
  req: express.Request,
  res: express.Response,
  next: express.NextFunction
) => {
  const authHeader = req.headers.authorization;
  if (!authHeader?.startsWith("Bearer ")) {
    return res.status(401).json({ error: "Unauthorized: no token" });
  }
  try {
    const token = authHeader.split("Bearer ")[1];
    req.user = await admin.auth().verifyIdToken(token);
    next();
  } catch {
    return res.status(401).json({ error: "Unauthorized: invalid token" });
  }
};

// Extend Express Request to include user
declare global {
  namespace Express {
    interface Request {
      user?: admin.auth.DecodedIdToken;
    }
  }
}

app.use(authenticate);

// ─── TOPICS ──────────────────────────────────────────────────────────────────

// GET /topics — all published topics
app.get("/topics", async (_req, res) => {
  try {
    const snapshot = await db
      .collection("topics")
      .where("isPublished", "==", true)
      .orderBy("orderIndex", "asc")
      .get();
    const topics = snapshot.docs.map((doc) => ({ topicId: doc.id, ...doc.data() }));
    res.json(topics);
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// GET /topics/:id — single topic
app.get("/topics/:id", async (req, res) => {
  try {
    const doc = await db.collection("topics").doc(req.params.id).get();
    if (!doc.exists) return res.status(404).json({ error: "Topic not found" });
    res.json({ topicId: doc.id, ...doc.data() });
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// GET /topics/:id/subtopics
app.get("/topics/:id/subtopics", async (req, res) => {
  try {
    const snapshot = await db
      .collection("topics")
      .doc(req.params.id)
      .collection("subtopics")
      .orderBy("orderIndex", "asc")
      .get();
    const subtopics = snapshot.docs.map((doc) => ({ subtopicId: doc.id, ...doc.data() }));
    res.json(subtopics);
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// GET /topics/:id/questions
app.get("/topics/:id/questions", async (req, res) => {
  try {
    const snapshot = await db
      .collection("questions")
      .where("topicId", "==", req.params.id)
      .orderBy("orderIndex", "asc")
      .get();
    const questions = snapshot.docs.map((doc) => ({ questionId: doc.id, ...doc.data() }));
    res.json(questions);
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// ─── VIDEOS ───────────────────────────────────────────────────────────────────

// GET /videos
app.get("/videos", async (_req, res) => {
  try {
    const snapshot = await db
      .collection("videos")
      .where("isPublished", "==", true)
      .orderBy("orderIndex", "asc")
      .get();
    const videos = snapshot.docs.map((doc) => ({ videoId: doc.id, ...doc.data() }));
    res.json(videos);
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// GET /videos/:id
app.get("/videos/:id", async (req, res) => {
  try {
    const doc = await db.collection("videos").doc(req.params.id).get();
    if (!doc.exists) return res.status(404).json({ error: "Video not found" });
    res.json({ videoId: doc.id, ...doc.data() });
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// GET /videos/topic/:topicId
app.get("/videos/topic/:topicId", async (req, res) => {
  try {
    const snapshot = await db
      .collection("videos")
      .where("topicId", "==", req.params.topicId)
      .where("isPublished", "==", true)
      .orderBy("orderIndex", "asc")
      .get();
    const videos = snapshot.docs.map((doc) => ({ videoId: doc.id, ...doc.data() }));
    res.json(videos);
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// ─── LEADERBOARD ─────────────────────────────────────────────────────────────

// GET /leaderboard — all-time top 50
app.get("/leaderboard", async (_req, res) => {
  try {
    const snapshot = await db
      .collection("leaderboard")
      .orderBy("totalPoints", "desc")
      .limit(50)
      .get();
    const entries = snapshot.docs.map((doc, i) => ({
      userId: doc.id,
      rank: i + 1,
      ...doc.data(),
    }));
    res.json(entries);
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// GET /leaderboard/weekly
app.get("/leaderboard/weekly", async (_req, res) => {
  try {
    const snapshot = await db
      .collection("leaderboard")
      .orderBy("weeklyPoints", "desc")
      .limit(50)
      .get();
    const entries = snapshot.docs.map((doc, i) => ({
      userId: doc.id,
      rank: i + 1,
      ...doc.data(),
    }));
    res.json(entries);
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// ─── USER PROGRESS ────────────────────────────────────────────────────────────

// GET /user/:userId/progress
app.get("/user/:userId/progress", async (req, res) => {
  const callerUid = req.user?.uid;
  if (callerUid !== req.params.userId) {
    return res.status(403).json({ error: "Forbidden" });
  }
  try {
    const snapshot = await db
      .collection("users")
      .doc(req.params.userId)
      .collection("progress")
      .get();
    const progress = snapshot.docs.map((doc) => doc.data());
    res.json(progress);
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// POST /user/progress
app.post("/user/progress", async (req, res) => {
  const userId = req.user?.uid;
  if (!userId) return res.status(401).json({ error: "Unauthorized" });
  const { topicId, completed, percentComplete } = req.body;
  if (!topicId) return res.status(400).json({ error: "topicId required" });
  try {
    await db
      .collection("users")
      .doc(userId)
      .collection("progress")
      .doc(topicId)
      .set(
        {
          topicId,
          completed: completed ?? false,
          percentComplete: percentComplete ?? 0,
          lastUpdated: admin.firestore.Timestamp.now(),
        },
        { merge: true }
      );
    res.status(204).send();
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// POST /user/video/watched
app.post("/user/video/watched", async (req, res) => {
  const userId = req.user?.uid;
  if (!userId) return res.status(401).json({ error: "Unauthorized" });
  const { videoId } = req.body;
  if (!videoId) return res.status(400).json({ error: "videoId required" });
  try {
    // Award points
    const userRef = db.collection("users").doc(userId);
    await db.runTransaction(async (t) => {
      const userSnap = await t.get(userRef);
      const currentPts = userSnap.data()?.totalPoints ?? 0;
      const newPts = currentPts + 30;
      t.update(userRef, {
        totalPoints: newPts,
        level: newPts >= 5000 ? "Expert" : newPts >= 2000 ? "Intermediate" : "Beginner",
        lastActive: admin.firestore.Timestamp.now(),
      });
    });
    // Update leaderboard
    const userSnap = await userRef.get();
    const userData = userSnap.data();
    const lbRef = db.collection("leaderboard").doc(userId);
    await db.runTransaction(async (t) => {
      const lbSnap = await t.get(lbRef);
      const cur = lbSnap.data() ?? {};
      t.set(lbRef, {
        userId,
        displayName: userData?.displayName ?? "Champion",
        photoUrl: userData?.photoUrl ?? "",
        totalPoints: (cur.totalPoints ?? 0) + 30,
        weeklyPoints: (cur.weeklyPoints ?? 0) + 30,
        videosWatched: (cur.videosWatched ?? 0) + 1,
        quizzesCompleted: cur.quizzesCompleted ?? 0,
        topicsCompleted: cur.topicsCompleted ?? 0,
        level: userData?.level ?? "Beginner",
        lastUpdated: admin.firestore.Timestamp.now(),
      });
    });
    res.status(204).send();
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// ─── QUIZ SUBMIT ──────────────────────────────────────────────────────────────

// POST /quiz/submit
app.post("/quiz/submit", async (req, res) => {
  const userId = req.user?.uid;
  if (!userId) return res.status(401).json({ error: "Unauthorized" });

  const { topicId, answers } = req.body as {
    topicId: string;
    answers: { questionId: string; selectedAnswer: string }[];
  };

  if (!topicId || !Array.isArray(answers)) {
    return res.status(400).json({ error: "topicId and answers required" });
  }

  try {
    // Fetch questions
    const qSnap = await db
      .collection("questions")
      .where("topicId", "==", topicId)
      .get();

    const questionMap = new Map<
      string,
      { correctAnswer: string; explanation: string; points: number }
    >();
    qSnap.docs.forEach((doc) => {
      const d = doc.data();
      questionMap.set(doc.id, {
        correctAnswer: d.correctAnswer,
        explanation: d.explanation,
        points: d.points ?? 10,
      });
    });

    // Grade answers
    let score = 0;
    const results = answers.map((ans) => {
      const q = questionMap.get(ans.questionId);
      const isCorrect =
        q != null &&
        ans.selectedAnswer.trim().toLowerCase() ===
          q.correctAnswer.trim().toLowerCase();
      if (isCorrect) score++;
      return {
        questionId: ans.questionId,
        isCorrect,
        correctAnswer: q?.correctAnswer ?? "",
        explanation: q?.explanation ?? "",
      };
    });

    const totalQuestions = questionMap.size;
    const percentage = totalQuestions > 0 ? (score / totalQuestions) * 100 : 0;
    const passed = percentage >= 70;
    let pointsEarned = 0;
    if (percentage >= 100) pointsEarned = 200;
    else if (passed) pointsEarned = 100;

    // Save result to Firestore
    const resultRef = db
      .collection("users")
      .doc(userId)
      .collection("quizResults")
      .doc();
    await resultRef.set({
      resultId: resultRef.id,
      topicId,
      score,
      totalQuestions,
      percentage,
      passed,
      takenAt: admin.firestore.Timestamp.now(),
    });

    // Award points and update leaderboard
    if (pointsEarned > 0) {
      const userRef = db.collection("users").doc(userId);
      await db.runTransaction(async (t) => {
        const userSnap = await t.get(userRef);
        const curPts = userSnap.data()?.totalPoints ?? 0;
        const newPts = curPts + pointsEarned;
        t.update(userRef, {
          totalPoints: newPts,
          level: newPts >= 5000 ? "Expert" : newPts >= 2000 ? "Intermediate" : "Beginner",
          lastActive: admin.firestore.Timestamp.now(),
        });
      });

      const userSnap = await db.collection("users").doc(userId).get();
      const userData = userSnap.data();
      const lbRef = db.collection("leaderboard").doc(userId);
      await db.runTransaction(async (t) => {
        const lbSnap = await t.get(lbRef);
        const cur = lbSnap.data() ?? {};
        const newTotal = (cur.totalPoints ?? 0) + pointsEarned;
        t.set(lbRef, {
          userId,
          displayName: userData?.displayName ?? "Champion",
          photoUrl: userData?.photoUrl ?? "",
          totalPoints: newTotal,
          weeklyPoints: (cur.weeklyPoints ?? 0) + pointsEarned,
          quizzesCompleted: passed ? (cur.quizzesCompleted ?? 0) + 1 : (cur.quizzesCompleted ?? 0),
          videosWatched: cur.videosWatched ?? 0,
          topicsCompleted: cur.topicsCompleted ?? 0,
          level: newTotal >= 5000 ? "Expert" : newTotal >= 2000 ? "Intermediate" : "Beginner",
          lastUpdated: admin.firestore.Timestamp.now(),
        });
      });
    }

    res.json({ score, totalQuestions, percentage, passed, pointsEarned, results });
  } catch (err) {
    res.status(500).json({ error: String(err) });
  }
});

// Export
exports.api = functions.https.onRequest(app);
