(ns web-projects.layout
  (:require [hiccup.page :as page]))

(defn layout [line & content]
  (let [tagline (or line "Welcome to Web-Projects")]
    (page/html5
     [:head
      [:title tagline]
      (page/include-css "/css/style.css")]
     [:body
      [:div#header
       [:h1 tagline]]
      [:div#main content]
      (page/include-js "https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js")
      (page/include-js "/js/compiled/web-projects.js")])))
