(ns aws.sdk.imds
  "Provide access to Amazon's instance metadata service from an EC2 instance.")


(def imds-url "http://169.254.169.254")

(def imds-version "2012-01-12")


(defn ^:private get-imds-url [^String path]
  "Return the full URL for a given IMDS path."
  (str imds-url "/" imds-version "/" path))

(defn get-imds-by-path [^String path]
  "Return the IMDS data for a given path, 
   otherwise nil if FileNotFound."
  (try
    (let [resp (slurp (get-imds-url path))] 
      (if (and resp (not (.isEmpty resp)))
        resp
        nil))
    (catch java.io.FileNotFoundException e nil)))

(defn get-user-data []
  "Return the instance's user-data, otherwise nil"
  (get-imds-by-path "user-data"))

(defn get-instance-id []
  "Return the instance's instance-id, otherwise nil"
  (get-imds-by-path "meta-data/instance-id"))

(defn get-instance-type []
  "Return the instance's type, otherwise nil"
  (get-imds-by-path "meta-data/instance-type"))

(defn get-ami-id []
  "Return the instance's ami-id, otherwise nil"
  (get-imds-by-path "meta-data/ami-id"))

(defn get-ami-launch-index []
  "Return the instance's AMI launch index, otherwise nil"
  (if-let [id (get-imds-by-path "meta-data/ami-launch-index")]
    (Long/parseLong id)))

(defn get-public-ip []
  "Return the instance's public IP address, otherwise nil"
  (get-imds-by-path "meta-data/public-ipv4"))

(defn get-public-hostname []
  "Return the instance's public hostname, otherwise nil"
  (get-imds-by-path "meta-data/public-hostname"))

(defn get-local-ip []
  "Return the instance's local IP address, otherwise nil"
  (get-imds-by-path "meta-data/local-ipv4"))

(defn get-local-hostname []
  "Return the instance's local hostname, otherwise nil"
  (get-imds-by-path "meta-data/local-hostname"))

(defn get-mac []
  "Return the instance's MAC address, otherwise nil"
  (get-imds-by-path "meta-data/mac"))

(defn get-az []
  "Return the instance's availability zone, otherwise nil"
  (get-imds-by-path "meta-data/placement/availability-zone"))

(defn get-security-groups []
  "Return the instance's security groups, otherwise nil"
  (get-imds-by-path "meta-data/security-groups"))

(defn get-all []
  "Return a Clojure map containing most of the core IMDS data.
   If a field isn't defined, or is empty, it'll be stored as nil."
  {:user-data (get-user-data)
   :instance-id (get-instance-id)
   :instance-type (get-instance-type)
   :ami-id (get-ami-id)
   :ami-launch-index (get-ami-launch-index)
   :public-ip (get-public-ip)
   :public-hostname (get-public-hostname)
   :local-ip (get-local-ip)
   :local-hostname (get-local-hostname)
   :mac (get-mac)
   :az (get-az)
   :security-groups (get-security-groups)
   })
