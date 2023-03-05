package com.example.clocks

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.clocks.databinding.FragmentClockBinding
import com.example.clocks.viewmodel.*

/**
 * A simple [Fragment] subclass.
 * Use the [ClockFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClockFragment : Fragment() {

    private var _binding: FragmentClockBinding? = null
    private val binding get() = _binding!!

    private val textSizeVM: TextSizeViewModel by activityViewModels()
    private val circleWidthVM: CircleWidthViewModel by activityViewModels()
    private val handHourWidthVM: HandHourWidthViewModel by activityViewModels()
    private val handMinuteWidthVM: HandMinuteWidthViewModel by activityViewModels()
    private val handSecondWidthVM: HandSecondWidthViewModel by activityViewModels()
    private val markerLengthVM: MarkerLengthViewModel by activityViewModels()
    private val markerWidthVM: MarkerWidthViewModel by activityViewModels()
    private val markerSpecialLengthVM: MarkerSpecialLengthViewModel by activityViewModels()
    private val markerSpecialWidthVM: MarkerSpecialWidthViewModel by activityViewModels()
    private val circleInnerColorVM: CircleInnerColorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentClockBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            circleInnerColorVM.saveColor(
                Color.red(clockView.cirlceInnerColor),
                Color.green(clockView.cirlceInnerColor),
                Color.blue(clockView.cirlceInnerColor),
                Color.alpha(clockView.cirlceInnerColor)
            )
            textSizeVM.resultVM.observe(viewLifecycleOwner) { textSize ->
                clockView.numberTextSize = textSize
            }
            circleWidthVM.resultVM.observe(viewLifecycleOwner) { circleWidth ->
                clockView.circleWidth = circleWidth
            }
            handHourWidthVM.resultVM.observe(viewLifecycleOwner) { handWidth ->
                clockView.handHourWidth = handWidth
            }
            handMinuteWidthVM.resultVM.observe(viewLifecycleOwner) { handWidth ->
                clockView.handMinuteWidth = handWidth
            }
            handSecondWidthVM.resultVM.observe(viewLifecycleOwner) { handWidth ->
                clockView.handSecondWidth = handWidth
            }
            markerLengthVM.resultVM.observe(viewLifecycleOwner) { length ->
                clockView.markerLength = length
            }
            markerWidthVM.resultVM.observe(viewLifecycleOwner) { width ->
                clockView.markerWidth = width
            }
            markerSpecialLengthVM.resultVM.observe(viewLifecycleOwner) { length ->
                clockView.markerSpecialLength = length
            }
            markerSpecialWidthVM.resultVM.observe(viewLifecycleOwner) { width ->
                clockView.markerSpecialWidth = width
            }
            circleInnerColorVM.resultVM.observe(viewLifecycleOwner) { color ->
                clockView.cirlceInnerColor = color
            }
        }

    }

}