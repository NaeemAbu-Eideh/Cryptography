
import tkinter as tk
import random


def gcd(x,y):
    if y == 0:
        return x
    else:
        return gcd(y,x%y)

def setE(finN):
    x = random.randint(1,finN)
    count = 0
    while(gcd(x,finN) != 1):
        x = random.randint(1,finN)
        count = count+1
    return x 

def setD(finN,E):
    i = 1
    while(True):
        x = (i*finN) + 1
        x = x/E
        y = int(x)
        if (x-y) == 0:
            return y
        i = i+1

def isPrime(x):
    count = 0
    for i in range (1,int(x)+1):
        if x%i == 0:
            count+=1
    if count == 2:
        return True
    else:
        return False

def setRandom():
    x = random.randint(1,1000)
    return x
   
def setPrimeNumber():
    x = setRandom()
    while(isPrime(x) != True):
        x = setRandom()
    return x

def result(num, power , sub):
    y = num ** power
    y = y % sub
    return y

def insertKeys():
       map["p"] = setPrimeNumber()
       map["q"] = setPrimeNumber()
       map["n"] = map["p"] * map["q"]
       map["phi"] = (map["p"]-1) * (map["q"] -1)
       map["e"] = setE(map["phi"])
       map["d"] = setD(map["phi"],map["e"])

def setString():
    str = ""
    k = map["p"]
    str += f"p = {k} , "
    k = map["q"]
    str += f"q = {k} , "
    k = map["n"]
    str += f"n = {k} , "
    k = map["phi"]
    str += f"phi = {k} , "
    k = map["e"]
    str += f"e = {k} , "
    k = map["d"]
    str += f"d = {k}"
    return str

def setKeys():
    insertKeys()
    key.config(text=setString())

def selectButton1():
    setValue.config(state="disabled")
    map["select"] = "b1"

def remove():
    value.delete(0,tk.END)
    resultValue.config(text="")    

def selectButton2():
    setValue.config(state="active")
    map["select"] = "b2"
    
def work():
    str = value.get()
    if map["select"] == "b1":
        ciphertext = ""
        i = 0
        while(i < len(str)):
            x = ord(str[i])
            if x < 0: x+=256
            x = result(x,map["e"],map["n"])
            c = chr(x)
            ciphertext+=c
            i+=1
        resultValue.config(text=ciphertext)    

    elif map["select"] == "b2":
        plaintext = ""
        i = 0
        while(i < len(str)):
            x = ord(str[i])
            if x < 0 : x+=256
            x = result(x,map["d"],map["n"])
            c = chr(x)
            plaintext+=c
            i+=1
        resultValue.config(text=plaintext)
        
def getText():
    str = resultValue.cget("text")
    value.delete(0,tk.END)
    value.insert(0,str)    

def dispose():
    root.destroy()
                

map = {
    "p":0,"q":0,"n":0,"phi":0,"e":0,"d":0,"select":""
}
insertKeys()

plaintext = ""
ciphertext = ""

root = tk.Tk()
root.title("RSA Cipher")

frame0 = tk.Frame(root, width=500, height=500)


frame1 = tk.Frame(frame0)
textValue = tk.Label(frame1,text="enter a text: ")
value = tk.Entry(frame1)
textValue.pack(side="left")
value.pack(side="left")
frame1.pack()

frame2 = tk.Frame(frame0)
option = tk.BooleanVar()
enc = tk.Radiobutton(frame2,text="Encryption", variable=option, value="option1",command=selectButton1)
dec = tk.Radiobutton(frame2,text="Decryption", variable=option, value="option2",command=selectButton2)
enc.pack(side="left")
dec.pack(side="left")
frame2.pack()


frame3 = tk.Frame(frame0)
str = setString()
keyValues = tk.Label(frame3,text="Keys values: ")
key = tk.Label(frame3,text= str)
key.config(state="disabled")
keyValues.pack(side="left")
key.pack(side="left")
frame3.pack()

frame4 = tk.Frame(frame0)
resultText = tk.Label(frame4,text="Result: ")
resultValue = tk.Label(frame4)
resultText.pack(side="left")
resultValue.pack(side="left")
frame4.pack()



frame5 = tk.Frame(frame0)
l0 = tk.Label(frame5,text="  ")
l1 = tk.Label(frame5,text="  ")
l2 = tk.Label(frame5,text="  ")
l3 = tk.Label(frame5,text="  ")
setValue = tk.Button(frame5,text="encruption value",command=getText)
setValue.config(state="disabled")
ok = tk.Button(frame5,text="OK",command=work)
clear = tk.Button(frame5,text="Clear",command=remove)
changeKeys = tk.Button(frame5,text="Change Keys",command=setKeys)
exit = tk.Button(frame5,text="Exit",command=dispose)
ok.pack(side="left")
l0.pack(side="left")
clear.pack(side="left")
l1.pack(side="left")
changeKeys.pack(side="left")
l2.pack(side="left")
exit.pack(side="left")
l3.pack(side="left")
setValue.pack(side="left")
frame5.pack()







frame0.pack()
root.mainloop()
