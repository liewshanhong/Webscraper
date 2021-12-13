 
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn_extra.cluster import KMedoids
import seaborn as sns
newWords=[]
nums=[]

file = open('Processed skills\Engineerprocessed.txt','r',encoding='utf-8',errors='ignore')
allWords = file.read().split("\n")
stop = open('stop_words_english.txt','r',encoding='utf-8',errors='ignore')
stopz=stop.read().split("\n")
i=0
for words in allWords:
    if words.lower().strip() in stopz:
        allWords.remove(words)
        i=i+1
     
for word in range(0,len(allWords)):
    if allWords[word].lower().strip() not in newWords:
        newWords.append(allWords[word].lower().strip())
        nums.append(1)
    else:
        nums[newWords.index(allWords[word].lower().strip())]+=1

 
x = np.dstack((newWords,nums))
x=x.reshape(-1,2)
df=pd.DataFrame(x,columns=['words','nums'])
df['nums'] = pd.to_numeric(df['nums'])
df=df.sort_values(by=['nums'],ascending=False )
df=df.head(20)
df.head(20)
 
plt.barh(df['words'],df['nums'],color=sns.color_palette())
plt.title('Job Skills(via bag of words)')
plt.ylabel('Job Skill')
plt.xlabel('# of times appeared')
plt.show()
 
