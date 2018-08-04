(ns paulrd.server
  (:require [ring.util.response :as response]
            [immutant.web :as web]
            [reitit.ring :as ring]
            [environ.core :refer [env]]))

(defn handler [_]
  (-> (response/resource-response "index.html" {:root "public"})
      (response/content-type "text/html")))

(def app
  (ring/routes
   (ring/create-resource-handler {:path "/"})
   (ring/ring-handler (ring/router ["/*" {:get handler}]))
   (ring/create-default-handler)))

(defn run-server [port]
  (let [port (Integer. (or port (env :port) 5000))]
    (web/run app :port port)))

(defn -main [& [port]]
  (run-server port))

(comment
  (def s (web/run-dmc app))
  (web/stop s)
  (app {:request-method :post :uri "/css"})
  (app {:request-method :get :uri "/"})
  )
