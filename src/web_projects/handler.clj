(ns web-projects.handler
  (:require [web-projects.funcs :refer :all]
            [web-projects.layout :refer :all]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.core :as h]))

(defn front-page []
  (->>
   (h/html [:div.page
            [:p "this website is a silly little site where I learn clojure and put the results online"]
            [:p "here is a list of stuff I have made so far"]
            [:ul
             [:li [:a {:href "/f/100"} "a fizzbuzz for 100 numbers, you can change the url"]]
             [:li [:a {:href "/p/racecar"} "a palindrome checker, you change the url"]]]])
   (layout "welcome to web-projects")))

(defn palindrome-page
  "The function that contructs the palindrome page"
  [word]
  (->>
   (h/html [:p (str "is "
                    word
                    " a palindrome? "
                    (if (palindrome? word)
                      "yes"
                      "no")
                    ".")])
   (layout "this is the palindrome page")))

(defn fizzbuzz-page
  "The function that constructs the fizzbuzz page"
  [num]
  (->>
   (h/html [:ol (map
                 (fn [number]
                   [:li (fizzbuzz number)])
                 (range 1 (+ 1 (Integer. num))))])
   (layout "this is the fizzbuzz page")))

(defroutes app-routes
  (GET "/" [] (front-page))
  (GET "/p/:word" [word] (palindrome-page word))
  (GET "/f/:num" [num] (fizzbuzz-page num))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
