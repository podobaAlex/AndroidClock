package com.example.clocks

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.clocks.databinding.FragmentSettingsBinding
import com.example.clocks.viewmodel.*

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
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

    private lateinit var textSizeSeekBar: SeekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textSizeVM.resultVM.observe(viewLifecycleOwner) { textSize ->
            binding.textSizeSb.progress = pxToDp(textSize).toInt()
        }

        with(binding) {
            textSizeSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    textSizeVM.saveTextSize(dpToPx(p1.toFloat()))
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            circleWidthSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    circleWidthVM.saveCircleWidth(dpToPx(p1.toFloat()))
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            handHourWidthSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    handHourWidthVM.saveHandWidth(dpToPx(p1.toFloat() / 10))
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            handMinuteWidthSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    handMinuteWidthVM.saveHandWidth(dpToPx(p1.toFloat() / 10))
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            handSecondWidthSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    handSecondWidthVM.saveHandWidth(dpToPx(p1.toFloat() / 10))
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            markerLengthSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    markerLengthVM.saveLength(dpToPx(p1.toFloat() / 10))
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            markerWidthSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    markerWidthVM.saveWidth(dpToPx(p1.toFloat() / 10))
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            markerSpecialLengthSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    markerSpecialLengthVM.saveLength(dpToPx(p1.toFloat() / 10))
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            markerSpecialWidthSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    markerSpecialWidthVM.saveWidth(dpToPx(p1.toFloat() / 10))
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            innerCircleColorRedSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    circleInnerColorVM.saveColor(red = p1)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            innerCircleColorGreenSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    circleInnerColorVM.saveColor(green = p1)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            innerCircleColorBlueSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    circleInnerColorVM.saveColor(blue = p1)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            innerCircleColorAlphaSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    circleInnerColorVM.saveColor(alpha = p1)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

        }
    }

    private fun dpToPx(dp: Float) = dp * requireContext().resources.displayMetrics.density
    private fun pxToDp(px: Float) = px / requireContext().resources.displayMetrics.density

}