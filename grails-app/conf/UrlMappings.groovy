class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here

            }
        }

        "/" (controller: "user", action: "urlRedirect")


        "500"(view:'/error')
	}
}
