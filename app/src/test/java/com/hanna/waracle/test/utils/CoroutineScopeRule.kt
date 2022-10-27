@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hanna.waracle.test.utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * MainCoroutineScopeRule
 *
 * for main thread unit test scopes
 */
class MainCoroutineScopeRule(
    val testDispatcher: TestDispatcher = StandardTestDispatcher(),
) : TestWatcher(), CoroutineScope by TestScope(testDispatcher) {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}




