package com.example.easysend.network.api

/*
class AccessTokenAuth @Inject constructor(
    val repo:AuthRepository
)  : Authenticator{

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken: String = repo.getAuthToken(isRemote = false)
        if (!isRequestWithAccessToken(response) || accessToken == null) {
            return null
        }
        synchronized(this) {
            val newAccessToken: String = repo.getAuthToken(isRemote = true)
            // Access token is refreshed in another thread.
            if (accessToken != newAccessToken) {
                return newRequestWithAccessToken(response.request(), newAccessToken)
            }
            // Need to refresh an access token
            val updatedAccessToken: String = repo.refreshAccessToken(newAccessToken)
            return newRequestWithAccessToken(response.request(), updatedAccessToken)
        }
    }

    private fun isRequestWithAccessToken( response: Response): Boolean {
        val header = response.request().header("Authorization")
        return header != null && header.startsWith("Bearer")
    }

    private fun newRequestWithAccessToken(request: Request, accessToken: String): Request? {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }
}*/