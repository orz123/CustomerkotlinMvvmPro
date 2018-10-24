package com.orz.kotlin_mvvm_demo.core.view.main

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.KeyEvent
import android.widget.Toast
import com.chaychan.library.BottomBarItem
import com.chaychan.library.BottomBarLayout
import com.orz.kotlin_mvvm_demo.comment.vm.ResourceObserver
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.base.BaseActivity
import com.orz.kotlin_mvvm_demo.core.view.main.fragment.ContractFragment
import com.orz.kotlin_mvvm_demo.core.view.main.fragment.HomeFragment
import com.orz.kotlin_mvvm_demo.core.view.main.fragment.MineFragment
import com.orz.kotlin_mvvm_demo.core.view.main.fragment.WalletFragment
import com.orz.kotlin_mvvm_demo.core.vm.MainViewModel
import com.orz.kotlin_mvvm_demo.util.AppUtils
import com.orz.kotlin_mvvm_demo.util.FragmentManagerUtil
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(),HasSupportFragmentInjector,
        BottomBarLayout.OnItemSelectedListener{
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var mViewModel: MainViewModel
    private  val mFragmentList:List<Class<out Fragment>> =
            listOf(HomeFragment::class.java,WalletFragment::class.java,ContractFragment::class.java,MineFragment::class.java)
    private lateinit var fragmentManagerUtil: FragmentManagerUtil
    private var exitTime: Long = 0

    override val layoutId: Int by lazy {  R.layout.activity_main }
    override val isInject: Boolean by lazy { true }


    override fun configViews() {
        mViewModel.requestAppUpdate(AppUtils.getVersionCode(this)).observe(this, ResourceObserver(
                onSuccess = {
                    it?.let {

                    }
                },
                onError = {

                }))
        initListener()

    }

    private fun initListener() {
        bottomBarLayout.setOnItemSelectedListener(this)
    }

    override fun initData() {
        fragmentManagerUtil = FragmentManagerUtil(R.id.fl_content,supportFragmentManager,mFragmentList)
        fragmentManagerUtil.switchFragment(0) //默认显示第一页
    }

    override fun onItemSelected(bottomBarItem: BottomBarItem?, prePosition: Int, currentPosition: Int) {
        fragmentManagerUtil.switchFragment(currentPosition)
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    companion object {
        fun jumpActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            ContextCompat.startActivity(context,intent,null)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(applicationContext, resources.getText(R.string.hint_app_exit), Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                finish()
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
