package com.jnfran92.kotcoin.ui

/**
 * Interface for each ViewListener in the UI for handling [T] objects.
 * It is the interface between the UI and the Controller.
 */
interface ViewListener<T> {
    /**
     * Show the loading viewListener.
     */
    fun showLoading()

    /**
     * Hide the loading viewListener.
     */
    fun hideLoading()

    /**
     * Show the error message
     */
    fun showErrorMessage(message:String)

    /**
     * Show the retry viewListener.
     */
    fun showRetry()

    /**
     * Hide the retry viewListener.
     */
    fun hideRetry()

    /**
     * Show the data viewListener.
     */
    fun showData(t:T)

    /**
     * Show the data viewListener.
     */
    fun showDataList(t:List<T>)
}