'''
AirBNB Auto Downloader

This program uses a csv, abnb3.csv, to batch store files from http://insideairbnb.com/get-the-data.html

Files will be saved in folders using this format:
    nameOfCity/dataSubset/cityName_date_AirBNBFileTYpe

    Examples: 
        los-angeles/data/los-angeles_42249_calendar.csv
        los-angeles/visualisations/los-angeles_42210_neighbourhoods.csv
        new-orleans/data/new-orleans_42249_reviews.csv.gz

Be advised, do not run this script on all the data points. It will time out. You 
should preset the range before using this script or adjust the number of rows in
abnb3.csv. This script can be modified for other purposes.
'''

import urllib.request
import os

myFile = open("abnb3.csv",'r')
addressList = []
address = ""
count = 0
startRange = 448 #line you want to start downloading (inclusive)
endRange = 450 #line you want to finish downloading (exclusive)

for lines in myFile:
        #this is the start of US Data feel free to customize the starting point
        if (count < startRange):
                count += 1
                addressList.append(lines.split(","))
                continue
        else:
                addressList.append(lines.split(","))
                directory = str(addressList[count-1][3]+"/"+addressList[count-1][5]+"/")
                if not os.path.exists(directory):
                    os.makedirs(directory)
                nameOfFile = str(directory+addressList[count-1][3]+"_"+(addressList[count-1][4]).replace('/','_') + "_" + addressList[count-1][6]).strip()
                testFile = urllib.request.urlretrieve(addressList[count-1][0],nameOfFile)
                count+=1
                print(nameOfFile)
                #this is the end range to stop downloading data
                if (count == endRange):
                    break
