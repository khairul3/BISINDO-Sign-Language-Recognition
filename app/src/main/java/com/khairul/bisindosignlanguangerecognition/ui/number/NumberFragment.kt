package com.khairul.bisindosignlanguangerecognition.ui.number

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.khairul.bisindosignlanguangerecognition.R
import com.khairul.bisindosignlanguangerecognition.databinding.FragmentNumberBinding


class NumberFragment : Fragment() {
    private var binding: FragmentNumberBinding? = null
    private lateinit var adapter: NumberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NumberAdapter()

        binding?.let {
            with(it.rvNumber) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapter
                binding?.rvNumber?.layoutManager = GridLayoutManager(context, 2)
            }
        }
//
//        adapter.onItemClick = { selectedData ->
//            val intent = Intent(activity, DetailActivity::class.java)
//            intent.putExtra(DetailActivity.EXTRA_NUMBER, selectedData)
//            startActivity(intent)
//        }
    }


}