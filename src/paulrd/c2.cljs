(ns ^:figwheel-hooks paulrd.c2
    (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(goog-define debug false)

(println "This text is printed from src/paulrd.c2.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit this and watch it change!"]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(comment
  (reset! app-state {:text "yes yes no"})
  (reset! app-state {:text "Hello world!"})
  )
