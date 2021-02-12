package com.jnfran92.kotcoin.ui.crypto.navigator

import android.view.View
import androidx.navigation.findNavController
import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto
import com.jnfran92.kotcoin.ui.crypto.fragment.CryptoListFragmentDirections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoListNavigator @Inject constructor() {

    fun goToCryptoDetails(view: View, item: UICrypto) {
        val direction =
            CryptoListFragmentDirections.actionCryptoListFragmentToCryptoDetailsFragment(item)
        view.findNavController().navigate(direction)
    }
}