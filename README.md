# Kotcoin  [![Build Status](https://travis-ci.org/jnfran92/kotcoin.svg?branch=master)](https://travis-ci.org/jnfran92/kotcoin)

Crypto matters!

<img src="https://media.giphy.com/media/TzM708yJfPFcY/giphy.gif" alt="kotcoin-intro">


Android Application MVC-based and written all in Kotlin for retrieving 10 most capitalized 
Crypto-currencies from [CoinMarketCap](https://coinmarketcap.com/api) API (Note: a free API Key is needed).


## Current work

- Adding DataBinding
- Migrating to MVI
- Adding ViewModel instead of Controllers
- Adding Navigation JetPack
- Using Material Elements: Cards mainly!
- Using CLEAN structure modules: data + domain + presentation(app)
- Adding Use Cases
- Migrating to Hilt from Dagger
- Adding Workers for saving daily price
- Adding Room for local storage
- Adding Dynamic Modules
- Adding Compose()
- Adding Kotlin Flow!

## Requirements

- Dagger
- Retrofit
- Gson/Kotson
- AndroidRx
- Mockito (Just few tests)


## About Kotcoin

<img src="./app/src/main/res/drawable/backkotcoin.png" alt="kotcoin-background" width="250">

Kotcoin is based on Model View Controller Arch. This means that there is a controller which manages the 
data for every action coming from the view. Ideally, `Model` is separated from the view and 
the controller, since it is better for Unit Testing. 


The main model entity is `Crypto`, which represents a crypto-currency. This is based in JSON API info
provided by `CoinMarketCap` documentation.


`View` and `Controller` has dependencies of `Model`. `Model` has a FactoryClass to manage data sources.
`Controller` has a `ViewListener` Interface which have to be implemented by the `View` for handling
actions and requests.


Dependency Injection is performed at `App`, `Activity` and `Model` entity level by using `Dagger`. 
Lastly, API service is consumed by using `Retrofit`.

Data flow is managed by `reactive` Libraries. In such regard, all actions are managed by `Observable` 
objects, so data flows to an `Observer` when it is subscribed.


## Basic Guide

**IMPORTANT:** Add in `gradle.properties` your API KEY:

    kotcoinApiKey="xxxxxxxx-xxxx-xxxx-xxxxx-xxxxxxxxx"

Now, compile the project!


## Bugs

- When orientation change and there is no network connection, `androidRx` throws an Exception.

## What's Next!

- Add Crypto single views
- Add Controller methods for filter, apply, modify data.
- Unit, Integration and UI Testing
- Errors handling
- Add more languages
- Improve UI/UX

