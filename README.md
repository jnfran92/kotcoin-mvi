# Kotcoin

Crypto matters!

<img src="https://media.giphy.com/media/TzM708yJfPFcY/giphy.gif" alt="easy-wave-based">


Android Application MVC-based and written all in Kotlin for retrieving 10 most capitalized 
Crypto-currencies from [CoinMarketCap](https://coinmarketcap.com/api) (Note: a free API Key is needed).

## Requirements

- Dagger
- Retrofit
- Gson
- Kotson
- Android Rx

## Basic Guide

**IMPORTANT:** Add API Key to global `.gradle`, to do that (macOS):

    cd .gradle
    vim gradle.properties

Add in `gradle.properties` your API KEY:

    kotcoinApiKey="xxxxxxxx-xxxx-xxxx-xxxxx-xxxxxxxxx"

Now, compile the project!

## About Kotcoin:

Kotcoin is based on Model View Controller Arch. This means that there is a controller which manages the 
data for every action coming from the view. Ideally, `Model` is separated from the view and 
the controller, since it is better for Unit Testing. 


The main model entity is `Crypto`, which represents a crypto-currency. This is based in JSON API info
provided by `CoinMarketCap` documentation.


`View` and `Controller` has dependencies of `Model`. `Model` has a FactoryClass to manage data sources.
`Controller` implements a `ViewListener` which is the interface fro View Actions and Requests.


Dependency Injection is performed at `App`, `Activity` and `Model entity` level by using `Dagger`. 
Lastly, API service is consumed by using `Retrofit`.

## What's Next!

- Add Cyrpto single views
- Add Controller methods for filter, apply, modify data.
- Unit, Integration and UI Testing
- Errors handling
- Add more languages
- Improve UI/UX

