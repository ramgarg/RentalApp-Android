package com.eazyrento.supporting

import com.eazyrento.common.model.modelclass.DynamicKeyValue
import com.eazyrento.login.model.modelclass.LoginUserReqModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject

class MyJsonParser{
    companion object{
/*
*  Json array from json object with passing key as string
* */
         fun JsonArrayFromJsonObject(jsonObject: JsonObject, key:String):JsonArray? {
            return if(jsonObject.has(key)) jsonObject.getAsJsonArray(key) else null
        }
        /*
        * Conver json list into List object
        *
        * */

        inline fun <reified T>convertJSONListIntoList(jsonArray: JsonArray?): ArrayList<T>?{

            jsonArray?.let {

                val list:ArrayList<T> = ArrayList()

                for(jsonObject in it){
                  list.add(convertJsonObjectToObject(jsonObject as JsonObject))
            }
                return list
            }
            return null
        }
/*
*  convert json object into object
* */
        inline fun <reified T>convertJsonObjectToObject(jsonObject: JsonObject):T{
            return Gson().fromJson(jsonObject,T::class.java)
        }

        /*fun <T>convertObjectToJsonObject(obj: T):JsonObject{
            return Gson().toJson(obj)
        }*/
        /*
   * Dynamic parser object to
   * */

        fun dynamicKeyValueList(jsonObject: JsonObject):List<DynamicKeyValue>{
            val keys = jsonObject.keySet()
            val list = ArrayList<DynamicKeyValue>()

            for (key in keys){
                list.add(DynamicKeyValue(key,jsonObject.get(key).asString))
            }
            return list
        }

        fun parseFBData(jsonObject: JSONObject?,loginUserReqModel: LoginUserReqModel) {
            jsonObject?.let {
                if (it.has("email")){
                    loginUserReqModel.email =it.getString("email")
                }
            }

        }
    }



}