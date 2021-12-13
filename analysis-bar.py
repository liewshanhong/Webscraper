import math

def newNums(allWords):
    newWords=[]
    nums=[]
    for word in range(0,len(allWords)):
        if allWords[word].lower().strip() not in newWords:
            newWords.append(allWords[word].lower().strip())
            nums.append(1)
        else:
            nums[newWords.index(allWords[word].lower().strip())]+=1
    return nums
def bhattacharyya_coefficient(a,b):
    sum1 = 0
    sum2 = 0.0
    sum3 = 0.0
    sum4 = 0.0
    
    for i in range(0,len(a)):
        sum2 = sum2 + a[i]

    for i in range(0,len(b)):
        sum3 = sum3 + b[i]

    newNums = [x/sum2 for x in a]
    newNums1 = [x/sum3 for x in b]
    for i in range(0,min(len(a),len(b))):
        x = math.sqrt(newNums[i] * newNums1[i])
        sum1 = sum1 + x
    return sum1

f= open('Processed skills\Engineerprocessed.txt','r',encoding='utf-8',errors='ignore')
numsE=newNums(f.read().split('\n'))
f = open('Processed skills\salesProcessed.txt','r',encoding='utf-8',errors='ignore')
numsS=newNums(f.read().split('\n'))
print("bhattacharyya coefficient: " + str(bhattacharyya_coefficient(numsS,numsE)))