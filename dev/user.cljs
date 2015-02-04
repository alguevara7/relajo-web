(ns cljs.user
  (:require [relajo.app :as app]
            [figwheel.client :as figwheel :include-macros true]
            [ajax.core :refer [GET]]))

(enable-console-print!)

(figwheel/watch-and-reload
  :websocket-url "ws://localhost:3000/figwheel-ws"
  :jsload-callback (fn [] (app/main)))

(app/main)
