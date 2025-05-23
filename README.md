Coin App
==================

**Coin App** is a fully functional multiplatform app built entirely with Kotlin and Jetpack Compose. This
Follows design and development best practices.

https://github.com/user-attachments/assets/533a00a5-4a9b-4e83-8ca1-6b2cc44b2609

https://github.com/user-attachments/assets/d925cff4-9ea9-4ed5-988e-39c75b91b917


## Setup
- As it is a project made in KMM, if you want to generate the iOS version, you will need a MacOS.
- You will need the [CoinGecko API key](https://support.coingecko.com/hc/en-us/articles/21880397454233-User-Guide-How-to-sign-up-for-CoinGecko-Demo-API-and-generate-an-API-key)
- Put the key in the <b>local.properties</b> file. Ex: <b>API_KEY=YOURKEY</b>

## Tech stack & Open-source libraries
- Kotlin based, Jetpack Compose, Ktor, Coroutines flow + StateFlow for asynchronous.

- **Jetpack**
    - Compose: A modern toolkit for creating a native UI
    - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
    - Koin: for dependency injection.

- **Architecture**
    - MVI Architecture (Model - View - Intent)
    - Module Pattern
    - Repository Pattern
  
## Architecture overview

The app architecture has three layers: a [data](https://developer.android.com/jetpack/guide/data-layer), [domain](https://developer.android.com/topic/architecture/domain-layer) and [UI](https://developer.android.com/jetpack/guide/ui-layer)


<center>
<img src="images/architecture-overall.png" width="600px" alt="Diagram showing overall app architecture" />
</center>
