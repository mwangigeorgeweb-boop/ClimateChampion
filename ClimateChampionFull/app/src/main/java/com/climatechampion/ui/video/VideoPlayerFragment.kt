package com.climatechampion.ui.video

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.climatechampion.R
import com.climatechampion.databinding.FragmentVideoPlayerBinding
import com.climatechampion.model.Video
import com.climatechampion.network.NetworkResult
import com.climatechampion.utils.hide
import com.climatechampion.utils.show
import com.climatechampion.utils.toast
import com.climatechampion.viewmodel.VideoViewModel

class VideoPlayerFragment : Fragment() {

    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding get() = _binding!!

    private val args: VideoPlayerFragmentArgs by navArgs()
    private val viewModel: VideoViewModel by viewModels()

    private var currentVideo: Video? = null
    private lateinit var relatedAdapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRelatedRecycler()
        setupObservers()
        viewModel.loadVideoById(args.videoId)
    }

    private fun setupRelatedRecycler() {
        relatedAdapter = VideoAdapter { video ->
            val action = VideoPlayerFragmentDirections
                .actionVideoPlayerFragmentSelf(video.videoId)
            findNavController().navigate(action)
        }
        binding.rvRelatedVideos.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            adapter = relatedAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun setupObservers() {
        viewModel.selectedVideo.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    val video = result.data
                    currentVideo = video
                    bindVideoDetails(video)

                    loadYouTubeInWebView(video.videoUrl)
                    viewModel.loadRelatedVideos(video.topicId)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    toast(result.message)
                }
            }
        }

        viewModel.relatedVideos.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                val filtered = result.data.filter { it.videoId != args.videoId }
                relatedAdapter.submitList(filtered)
            }
        }

        viewModel.videoWatchedResult.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                toast(getString(R.string.points_awarded, 30))
            }
        }
    }

    private fun bindVideoDetails(video: Video) {
        binding.tvTitle.text = video.title
        binding.tvDescription.text = video.description
        binding.tvDuration.text = video.formattedDuration()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadYouTubeInWebView(videoUrl: String) {
        val webView: WebView = binding.youtubePlayerView

        webView.apply {
            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                mediaPlaybackRequiresUserGesture = false
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                // Set Desktop Chrome User-Agent to bypass YouTube webview restrictions
                userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36"
            }

            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBar.hide()
                }
            }

            val videoId = extractYoutubeId(videoUrl)

            // Construct direct embed URL
            val embedUrl = "https://www.youtube.com/embed/$videoId?autoplay=1&modestbranding=1&rel=0&playsinline=1"

            // Pass direct Referer header to fulfill YouTube domain verification
            val headers = HashMap<String, String>()
            headers["Referer"] = "https://www.youtube.com"

            loadUrl(embedUrl, headers)
        }
    }

    private fun extractYoutubeId(url: String): String {
        val cleanUrl = url.trim()
        return when {
            cleanUrl.contains("embed/") -> cleanUrl.substringAfter("embed/").substringBefore("?").substringBefore("&")
            cleanUrl.contains("watch?v=") -> cleanUrl.substringAfter("watch?v=").substringBefore("&")
            cleanUrl.contains("youtu.be/") -> cleanUrl.substringAfter("youtu.be/").substringBefore("?")
            else -> cleanUrl
        }
    }

    override fun onPause() {
        super.onPause()
        binding.youtubePlayerView.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.youtubePlayerView.onResume()
    }

    override fun onDestroyView() {
        binding.youtubePlayerView.destroy()
        super.onDestroyView()
        _binding = null
    }
}