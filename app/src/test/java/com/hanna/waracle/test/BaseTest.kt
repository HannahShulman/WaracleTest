package com.hanna.waracle.test


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hanna.waracle.test.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.rules.TestRule

open class BaseTest {

    @get:Rule
    val testScopeRule = MainCoroutineScopeRule()
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    val fakeDispatcher get() = testScopeRule.testDispatcher

    @OptIn(ExperimentalCoroutinesApi::class)
    fun runSuspendTest(testBody: suspend TestScope.() -> Unit): TestResult =
        runTest(testScopeRule.testDispatcher, testBody = testBody)

}