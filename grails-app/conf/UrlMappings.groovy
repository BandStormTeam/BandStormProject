class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here

            }
        }

        "/" (controller: "User", action: "homepage")
        "500"(view:'/error')
	}
}
