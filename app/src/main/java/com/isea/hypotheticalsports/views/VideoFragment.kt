package com.isea.hypotheticalsports.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.isea.hypotheticalsports.R
import com.isea.hypotheticalsports.databinding.FragmentVideoBinding


class VideoFragment : Fragment(R.layout.fragment_video) {

    private val navArgs: VideoFragmentArgs by navArgs()
    private var player: ExoPlayer? = null
    private lateinit var binding: FragmentVideoBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVideoBinding.bind(view)
        initializePlayer()
    }

    /**
     * @return Whether initialization was successful.
     */
    private fun initializePlayer() {
        if (player == null) {
            player = SimpleExoPlayer.Builder(requireContext()).build()
            binding.playerView.player = player
            val mediaItem: MediaItem =
                MediaItem.fromUri(navArgs.url)
            player!!.addMediaItem(mediaItem)
            player!!.prepare()
            player!!.playWhenReady = true
        }
    }

    override fun onPause() {
        super.onPause()
        binding.playerView.onPause()
        releasePlayer()
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
        binding.playerView.onResume()

    }

    override fun onDestroyView() {
        releasePlayer()
        super.onDestroyView()
    }

    private fun releasePlayer() {
        player?.release()
        player = null
        binding.playerView.player = null
    }

}