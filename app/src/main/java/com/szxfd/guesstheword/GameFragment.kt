package com.szxfd.guesstheword


import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.szxfd.guesstheword.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.eventGameFinish.observe(this, Observer { hasFinished ->
            if (hasFinished) onEndGame()
        })
        return binding.root
    }

    private fun onEndGame() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action =
            GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score.value ?: 0)
        findNavController().navigate(action)
        viewModel.onGameFinishComplete()
    }

}
