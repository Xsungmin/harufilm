package com.example.todayfilm

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todayfilm.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentHomeBinding
    var isFour = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 사진 4장이라면 isFour 변경

        // 데이터 넣어서 frame 프래그먼트 호출
        val frameFragment = FrameFragment()
        val bundle = Bundle()
        bundle.putString("parent", "home")
        frameFragment.arguments = bundle

        childFragmentManager.beginTransaction().add(R.id.fragment_content_home, frameFragment).commit()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.fragmentContentHome.setOnClickListener(this)
        binding.homeToComplete.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fragment_content_home -> {
                val intent = Intent(activity, CameraActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            R.id.home_to_complete -> {
                val builder = AlertDialog.Builder(activity)

                // 사진 4장 미만인 경우(isFour가 false인 경우) 다이얼로그
                if (!isFour) {
                    builder.setTitle("아직 필름이 다 채워지지 않았습니다.\n이대로 완성하시겠습니까?")
                        .setMessage("예 선택 시, 설정의 '사진 반복 여부'에 따라\n필름의 남은 칸을 채웁니다.")
                        .setPositiveButton("예", DialogInterface.OnClickListener { dialog, id ->
                            // 설정의 사진 반복 여부에 따라 남은 칸 채우기

                            completeDialog(builder)
                        })
                        .setNegativeButton("아니오", DialogInterface.OnClickListener { dialog, id -> })
                    builder.show()
                } else {
                    completeDialog(builder)
                }
            }
        }
    }

    private fun completeDialog(builder: AlertDialog.Builder) {
        builder.setTitle("오늘의 필름을 현상할까요?")
            .setMessage("예 선택 시, 더 이상 수정할 수 없습니다.")
            .setPositiveButton("예", DialogInterface.OnClickListener { dialog, id ->
                // complete 액티비티로 이동
                val intent = Intent(activity, CompleteActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            })
            .setNegativeButton("아니오", DialogInterface.OnClickListener { dialog, id -> })
        builder.show()
    }
}