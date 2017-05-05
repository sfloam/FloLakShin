Files:

1) finalfinal.csv - contains concatenated data after cleaning, analyzing (weather, flights, Airbnb)
2) v2_data_preprocess.ipynb - Used to preprocess data (including min-max scaling) and format it for Pyspark
3) m_occ_scaled_2.libsvm - data formatted to predicted occupancy rates
4) m_price_scaled_2.libsvm - data formatted to predicted price per night
5) 2_occ_pred_spark.py - Pyspark script to train a linear regression (with elastic net regularization) model to predict occupancy rate 
6) 2_price_pred_spark.py - Pyspark script to train a linear regression (with elastic net regularization) model to predict price 
7) pyspark_screenshot.png - screenshot of running pyspark script

To run:

1) Move files to your favorite Hadoop cluster (recommended to be run on NYU's Dumbo cluster)
2) Edit file path in script to match file location in Hadoop cluster
3) Execute the following command: spark-submit /path/to/file