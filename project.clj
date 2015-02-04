(defproject relajo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src" "dev"]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2505" :scope "provided"]
                 [figwheel "0.1.7-SNAPSHOT"]
                 [reagent "0.5.0-alpha"]
                 [cljs-ajax "0.3.3"]
                 [secretary "1.2.1"]
                 [fipp "0.5.1"]]

  :min-lein-version "2.5.0"

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [lein-figwheel "0.1.7-SNAPSHOT"]]

  :figwheel {
             :http-server-root "public"
             :server-port 3000
             :css-dirs ["resources/public/css"]
             }

  :cljsbuild {:builds {:app {:source-paths ["src" "dev"]
                             :compiler {:output-to "resources/public/js/app.js"
                                        :output-dir "resources/public/js/out"
                                        :source-map    "resources/public/js/out.js.map"
                                        :optimizations :none}}}}
  )
