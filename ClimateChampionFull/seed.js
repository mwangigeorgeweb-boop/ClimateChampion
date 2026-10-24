const { initializeApp, cert } = require('firebase-admin/app');
const { getFirestore } = require('firebase-admin/firestore');

const serviceAccount = require('./serviceAccountKey.json');
const data = require('./seed_data.json');

initializeApp({
  credential: cert(serviceAccount)
});

const db = getFirestore();

async function seedData() {
  console.log('Beginning clean root-level dataset seeding to Firestore...');

  for (const [key, items] of Object.entries(data)) {
    if (!Array.isArray(items)) continue;

    console.log(`\nSeeding collection: [${key}] (${items.length} items)...`);

    for (let i = 0; i < items.length; i++) {
      const item = items[i];
      let docId = null;

      // Safely determine document ID based on dataset key with fallbacks
      if (key === 'topics') docId = item.topicId || item.id;
      else if (key === 'videos') docId = item.videoId || item.id;
      else if (key === 'questions') docId = item.questionId || item.id;
      else if (key === 'leaderboard') docId = item.userId || item.id;

      // Fallback to random ID if lookup is missing or undefined
      const targetRef = docId ? db.collection(key).doc(String(docId)) : db.collection(key).doc();

      try {
        // Remove any accidental undefined fields from the object payload
        const sanitizedItem = JSON.parse(JSON.stringify(item));

        await targetRef.set(sanitizedItem);
        console.log(`   └ Document [${i + 1}/${items.length}]: ${targetRef.id}`);
      } catch (err) {
        console.error(`   ❌ Error saving item at index ${i} in collection [${key}]:`, err.message);
        console.error('Problematic item payload:', item);
        throw err;
      }
    }
  }

  console.log('\nSeeding complete! All root collections successfully populated.');
}

seedData().catch((err) => {
  console.error('Seeding failed fatally:', err);
  process.exit(1);
});