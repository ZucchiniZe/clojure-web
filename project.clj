(defproject web-projects "0.1.0-SNAPSHOT"
  :description "FIXME: write description"

  :url "http://example.com/FIXME"

  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]
                 [ring/ring-defaults "0.1.5"]
                 [compojure "1.4.0"]
                 [hiccup "1.0.5"]]

  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-3"]]

  :source-paths ["src"]
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :compiler {:main web-projects.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/web-projects.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/web-projects.js"
                           :main web-projects.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"]
             :open-file-command "emacsclient -n +$2 $1"}
  :ring {:handler web-projects.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
