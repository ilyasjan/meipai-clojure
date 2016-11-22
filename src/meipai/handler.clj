(ns meipai.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [clj-http.client :as client]
            [clostache.parser :refer :all]
            [clojure.string :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn get-con [url]
  (str (:body (client/get url))))

(def prefix "data-video=\"")

(def suffix ".mp4")

(defn get-mp4-url [con]
  (let [begin (+ (index-of con prefix) (.length prefix))
        end   (+ (index-of con suffix begin) (.length suffix))]
    (subs con begin end)))

(defroutes app-routes

  (GET "/" [] (io/resource "./public/index.html"))

  (GET "/ser" [url] 
       ;;(render-resource "public/show.mustach" {:url (get-mp4-url (get-con url))})
       (get-mp4-url (get-con url))
       )
  
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))


