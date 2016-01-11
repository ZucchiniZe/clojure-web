(ns web-projects.layout
  (:require [hiccup.page :as page]))

(defn layout [line & content]
  (let [tagline (or line "Welcome to Web-Projects")]
    (page/html5
     [:head
      [:title tagline]]
     [:body
      [:div#header
       [:h1 tagline]]
      [:div#main content]])))
