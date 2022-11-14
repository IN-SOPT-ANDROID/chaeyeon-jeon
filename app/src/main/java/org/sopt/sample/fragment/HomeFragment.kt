package org.sopt.sample.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import org.sopt.sample.R
import org.sopt.sample.adapter.FollowerAdapter
import org.sopt.sample.adapter.FollowerAdapter.Companion.VIEW_TYPE_HEADER
import org.sopt.sample.adapter.FollowerAdapter.Companion.VIEW_TYPE_ITEM
import org.sopt.sample.base.showSnackbar
import org.sopt.sample.data.FollowerViewModel
import org.sopt.sample.data.remote.ResponseGetFollowerListDTO
import org.sopt.sample.data.remote.ServicePool.followerService
import org.sopt.sample.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "binding value is null." }

    private val followerViewModel by viewModels<FollowerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        // 팔로워 데이터 초기화
        followerViewModel.followerList.clear()

        // Reqres Get List User API로 팔로워 목록 불러오기 (일단 1페이지로 요청)
        followerService.getFollowerList(1).enqueue(object : Callback<ResponseGetFollowerListDTO> {
            override fun onResponse(
                call: Call<ResponseGetFollowerListDTO>,
                response: Response<ResponseGetFollowerListDTO>
            ) {
                if (response.isSuccessful) {
                    Log.d("GET_FOLLOWER_LIST", "response : " + response.body().toString())

                    // 팔로워가 존재하지 않는 경우
                    if (response.body()?.data == null) {
                        context?.showSnackbar(binding.root, getString(R.string.msg_home_null))
                        return
                    }

                    // get follower list success
                    for (follower in response.body()?.data!!) followerViewModel.followerList.add(
                        follower
                    )

                    // 리사이클러뷰 어댑터, 레이아웃 매니저 설정
                    val adapter = FollowerAdapter(requireContext())
                    binding.rvFollower.adapter = adapter
                    adapter.setFollowerList(followerViewModel.followerList)

                    val layoutManager = GridLayoutManager(activity, 2)
                    layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            when (adapter.getItemViewType(position)) {
                                VIEW_TYPE_HEADER -> return 2
                                VIEW_TYPE_ITEM -> return 1
                                else -> throw ClassCastException("Unkown View Type : ${adapter.getItemViewType(position)}")
                            }
                        }
                    }
                    binding.rvFollower.layoutManager = layoutManager
                }
                // get follower list fail
                else {
                    context?.showSnackbar(binding.root, getString(R.string.msg_home_fail))
                    Log.e("GET_FOLLOWER_LIST", "code : " + response.code())
                    Log.e("GET_FOLLOWER_LIST", "message : " + response.message())
                }
            }

            override fun onFailure(call: Call<ResponseGetFollowerListDTO>, t: Throwable) {
                context?.showSnackbar(binding.root, getString(R.string.msg_home_fail))
                Log.e("GET_FOLLOWER_LIST", "message : " + t.message)
            }
        })
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}