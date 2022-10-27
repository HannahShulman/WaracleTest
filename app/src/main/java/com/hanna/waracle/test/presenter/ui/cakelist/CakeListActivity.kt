package com.hanna.waracle.test.presenter.ui.cakelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.hanna.waracle.test.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CakeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, CakeListFragment())
            }
        }
    }
}