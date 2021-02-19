# Kotcoin  [![Build Status](https://travis-ci.org/jnfran92/kotcoin.svg?branch=master)](https://travis-ci.org/jnfran92/kotcoin)


Crypto matters!

<img src="https://media.giphy.com/media/TzM708yJfPFcY/giphy.gif" alt="kotcoin-intro">


Android Application **MVI-based** and written all in Kotlin for retrieving 10 most capitalized 
Crypto-currencies from [CoinMarketCap](https://coinmarketcap.com/api) API (_Note: eventually you will need a free API Key_).


## Preview

<img src="./imgs/main.png" alt="kotcoin-intro" width="50%">

<img src="./imgs/details.png" alt="kotcoin-intro" width="50%">

## Features

- MVI Architecture
- Dependency Injection with **Hilt**
- Modularized App: Data + Domain + Presentation + UI
- Retrieve Data from cloud and store locally using **ROOM**

## Current work

- Adding DataBinding ✅
- Migrating to MVI ✅
- Adding ViewModel instead of Controllers ✅
- Adding Navigation JetPack ✅
- Using Material Elements: Cards mainly! ✅
- Using CLEAN structure modules: data + domain + presentation(app) ✅
- Adding Use Cases ✅
- Migrating to Hilt from Dagger ✅
- Adding Kotlin Flow! 🤔 _instead of rx java_ 
- Adding Tensorflow Lite! 
- Adding Lottie for animations 
- Adding MotionLayout for on-boardings 
- Adding a MockServer!
- Adding security wrapper API

## Requirements

- Hilt Dagger 
- Retrofit 
- Gson/Kotson 
- AndroidRx 
- Mockito (Just few tests) 


## About Kotcoin

<img src="./app/src/main/res/drawable/backkotcoin.png" alt="kotcoin-background" width="250">

Kotcoin is based on Model-View-Intent MVI Arch. DI handled by Dagger and Reactive data flow handled by
RXJava. SOLID and CLEAN based project!

## What's Next!

- Add Crypto single views
- Add Controller methods for filter, apply, modify data.
- Unit, Integration and UI Testing
- Errors handling
- Add more languages
- Improve UI/UX


