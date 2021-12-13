import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn_extra.cluster import KMedoids
import seaborn as sns


print("RUNNING")
file = open('Processed skills\Engineerprocessed.txt','r',encoding='utf-8',errors='ignore')
allWords = file.read().split("\n")
stop = open('stop_words_english.txt','r',encoding='utf-8',errors='ignore')
stopz=stop.read().split("\n")
i=0
for words in allWords:
    if words.lower().strip() in stopz:
        allWords.remove(words)
        i=i+1
         
newWords=[]      
nums=[]
for word in range(0,len(allWords)):
    if allWords[word].lower().strip() not in newWords:
        newWords.append(allWords[word].lower().strip())
        nums.append(1)
    else:
        nums[newWords.index(allWords[word].lower().strip())]+=1
    
listValue=[]
listLength=[]
for i in range(0,len(newWords)):
     listValue.append((sum([ord(char) for char in newWords[i]])/1000)) 
     listLength.append(nums[i])
    
x = np.dstack((listLength,listValue))
x=x.reshape(-1,2)
df=pd.DataFrame(x)


km = KMedoids(n_clusters=10,init='random').fit(x)
label=km.labels_
df['cluster']=label
df.head()
unique_labels=set(label)
colors = [
plt.cm.Spectral(each) for each in np.linspace(0, 1, len(unique_labels))
]
for k, col in zip(unique_labels, colors):
    class_member_mask = label == k
    xy = x[class_member_mask]
    plt.plot(
        xy[:, 0],
        xy[:, 1],
        "o",
        markerfacecolor=tuple(col),
        markeredgecolor="k",
        markersize=6)
plt.title('Job Skills(via clustering)')
plt.show()

x = np.dstack((newWords,nums))
x=x.reshape(-1,2)
df=pd.DataFrame(x,columns=['words','nums'])
df['cluster']=label
df['nums'] = pd.to_numeric(df['nums'])
df2=df.groupby('cluster').max()
df3=df.groupby('cluster').sum()
df2.reset_index(level=0, inplace=True)
df2.head(100)
 
x_tickn = df2['words'].to_numpy()
x_ticks=[]
for a in range(0,len(x_tickn)):
    x_ticks.append(a)
print(x_ticks)
plt.xticks(x_ticks,x_tickn,rotation=90)
plt.bar(df2['cluster'],df3['nums'],color=sns.color_palette())
plt.title('Job Skills(via clustering)')
plt.xlabel('Job Skill')
plt.ylabel('# of times appeared')
plt.show()
