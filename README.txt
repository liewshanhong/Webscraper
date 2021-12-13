In order to run code in this repository you must download corenlp from:
https://stanfordnlp.github.io/CoreNLP/
and extract its contents to the main folder.
Order must run in:
1.src/getLinks.java
2.src/getDescriptions.java
3.src/NLP.java
5.(analysis-bag of words.py,analysis-clustering.py,analysis-bar.py) These 3 can be run in any order

getLinks.java-This file reads the links for the 10000 job postings and saves them to a file in the links folder.MUST rename if you run algorithm more than once and want to keep previouse data. the link z must also be changes according the the job type you want to search(both locations).

getDescriptions.java- must change location of the links file to the links file you want to read aswell as the destination file if you want to preserve previously collected data. this file takes the links provided earlier and will scrape the job posting.

NOTE: For both of these files the r() method is used to bypass indeeds recapatcha system, it works throught the use of cisco any connect. It will require you to hardcode your passwords aswell as the location the cisco window will appear. If using robot function must be signed into vpn before starting. If you dont want to use this feature must comment out line 49 on getLinks.java  and 39 on getDescription.

NLP.java - must set file path to location of the jobs description file you would like to analyze. this file will perform the following 5 NLP functions on the document:
    1. sentence splitting
    2. tokenizing
    3. name entity recognition
    4. part of speach tagging
    5. lemmatization
like the previouse file you must also specify the destination you would like the resulting file to go to. Based on the nlp functions it will extract the job skills from each job description. This algorithm relatively accurate it sucesfully pulls all of the skills from the job postings, however it also pulls additional words that might not be considered a skill.

analysis-bag.py - this file runs a simple bag of words calculation and then plots the top 20 most common job skills for the given feild.

analysis-clustering.py - this file runs a k-mediod clustering(sklearn) on all of the job skills. this clustering is displayed when the page is run it also uses the information from the cluster to determine the most important skill within the clusters.

analysis-bar.py- this file calculates the Bhattacharyya coefficient between two different job types and prints the results.

Note: The graphs for the clustering aswell as the bag of words can be found in the graphs folder. Also for all of the graphing and data analysis we used a combination of pandas, matplotlib, sklearn, and seaborn.

Bugs/issues:
-Indeed.com Scraping. Because indeed.com has anti-scraping algorithms we were unable to get data for 20,000 job posting. indeed limits the results to 100 pages, then afterwards the data is the same. We did scrape all 20,000 postings howver a large chunk of that data is unusable and if it was used it would impact the integrity of the results.
-Job Skills extraction. Due to the fact the job descriptions are relatively complex it is challenging to get all of the job skills from the job descriptions. It is also challenging to determine what is a skill expected from you vs descriptions of the company. As a result of this the job skills extraction is not as accurate as we would like it to be.

Original plan:
    - *Using java
    - **Scrape indeed.com for engineering/pilot jobs. Get 20,000 job postings. 
    - use stanfords coreNLP to extract job skills
    - Analyse the data( we were not sure of the libraries originally)
    
    *We ended up using a combination of java and python as we were more comfortable doing the data analysis in python.

    **We ended up using engineering/sales jobs in order to ensure thier was going to be enought job postings.

File Structure:
    graphs - contains the graphs that were created.
        engineering
            bag of words.png
            cluster.png
            job skills by cluster.png
        sales
            bag of words.png
            sale skills by cluster.png
            sales-clusters.png
    jobDescriptions-10,000 job descriptions per title
        OutEngineering.txt
        OutSales.txt
    links-contains the 10,000 links for the job description. The links for engineering have been misplaced.
        salesLinks.txt

    Processed skills-list of all the job skills
        EngineeringProcessed.txt
        salesProcessed.txt
    src-contains code for scraping and skill extraction
        getDescriptions.java
        getLinks.java
        NLP.java

    analysis-bag of words.py
    analysis-bar.py
    analysis-clustering.py
    README.md

    
    
