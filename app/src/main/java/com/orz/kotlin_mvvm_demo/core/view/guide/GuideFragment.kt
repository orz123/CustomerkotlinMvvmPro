package com.orz.kotlin_mvvm_demo.core.view.guide

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orz.kotlin_mvvm_demo.R
import kotlinx.android.synthetic.main.fragment_guide.view.*

/**
 * @author: orz on 2018/9/20
 */
class GuideFragment: Fragment() {

    private lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mView = inflater.inflate(R.layout.fragment_guide, container, false)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    private fun initData() {
        val args: Bundle? = arguments
        args?.let {
            var imgId: Int = args.getInt(IMG_ID)
            mView.ivContent.setImageResource(imgId)
        }
    }

    companion object {
        private const val IMG_ID = "img_guide"
        fun newInstance(imgResId: Int): GuideFragment {
            val fragment = GuideFragment()
            val bundle = Bundle()
            bundle.putInt(IMG_ID, imgResId)
            fragment.arguments = bundle
            return fragment
        }
    }
}