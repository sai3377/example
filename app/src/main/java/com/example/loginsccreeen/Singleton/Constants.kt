package com.example.loginsccreeen.Singleton

class Constants {

    companion object {
        /*
         * APIS
         */
        const val TEST_URL = "http://192.168.1.49/cgd/services/PNG_Controller/"

        //private const val url = "http://202.53.92.122/cgd_api/"
        //
        private const val url = "http://cgddev.highgoweb.com/"

        //private const val url = "http://meghagasapi.highgoweb.com/"

        //Server files version number
        const val version_number = "_v_1_14"

        //http://cgddev.highgoweb.com/services/Auth_v_1_14/app_version/

        //url
        const val logInUrl = url + "services/Auth" + version_number + "/"
    }
}