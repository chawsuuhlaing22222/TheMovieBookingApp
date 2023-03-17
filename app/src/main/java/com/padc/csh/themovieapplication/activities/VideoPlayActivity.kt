package com.padc.csh.themovieapplication.activities

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.padc.csh.themovieapplication.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import kotlinx.android.synthetic.main.activity_movie_detail.vvMovie
import kotlinx.android.synthetic.main.activity_video_play.*


class VideoPlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)

        btnStat.setOnClickListener {
            setUpDefaultMovie()
        }


//        vvMoviePlayIcon.setOnClickListener {
//            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//                override fun onReady(youTubePlayer: YouTubePlayer) {
//                    val videoId = "S0Q4gqBUs7c"
//                    youTubePlayer.loadVideo(videoId, 0f)
//                }
//            })
//        }
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = "S0Q4gqBUs7c"
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })

//        youTubePlayerView.initialize(object :YouTubePlayerListener{
//            override fun onApiChange(youTubePlayer: YouTubePlayer) {
//
//            }
//
//            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
//
//            }
//
//            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
//
//            }
//
//            override fun onPlaybackQualityChange(
//                youTubePlayer: YouTubePlayer,
//                playbackQuality: PlayerConstants.PlaybackQuality
//            ) {
//
//            }
//
//            override fun onPlaybackRateChange(
//                youTubePlayer: YouTubePlayer,
//                playbackRate: PlayerConstants.PlaybackRate
//            ) {
//
//            }
//
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                val videoId = "S0Q4gqBUs7c"
//                youTubePlayer.loadVideo(videoId, 0f)
//            }
//
//            override fun onStateChange(
//                youTubePlayer: YouTubePlayer,
//                state: PlayerConstants.PlayerState
//            ) {
//
//            }
//
//            override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
//
//            }
//
//            override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
//
//            }
//
//            override fun onVideoLoadedFraction(
//                youTubePlayer: YouTubePlayer,
//                loadedFraction: Float
//            ) {
//
//            }
//
//        })

    }

    private fun setUpDefaultMovie(){
        val videoUrl =
            "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"

        vvMovie.setMediaController(MediaController(this))
        vvMovie.setVideoURI(Uri.parse(videoUrl))
        vvMovie.requestFocus()
        vvMovie.start()
    }
}