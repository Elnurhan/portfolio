# -*- coding: utf-8 -*-
from random import randint

def hangman(word):
    wrong = 0 # Кол-во ошибок
    stages = ["",
             "________     ",
             "|            ",
             "|       |    ",
             "|       O    ",
             "|      /|\   ",
             "|      / \   ",
             "|            ",
             ]
    rletters = list(word) # Делим слово на список букв
    board = ["_"] * len(word) # Поле на котором будут отображаться буквы
    win = False 
    print("Да начнётся казнь!")
    while wrong < len(stages) - 1: # Пока остались попытки
        print("\n")
        char = input("Введите букву: ").upper()
        if char not in rletters:
            wrong += 1
        else:
            while char in rletters:
                if char in rletters:
                    char_ind = rletters.index(char) # Индекс угаданой буквы
                    board[char_ind] = char # Открываем эту букву
                    rletters[char_ind] = '*' # И убираем её из списка букв, чтобы исклбчит возможность повторного угадывания
                else:
                    break
        #if char not in rletters:
            #wrong += 1 # При ошибке добавляем 1 к попыткам
        print((" ".join(board))) # 
        e = wrong + 1
        print("\n".join(stages[0: e]))
        if "_" not in board: # Если все буквы открыты
            print("Казнь откладывается на неопределённый срок! Вы угадали слово: ")
            print(" ".join(board))
            win = True
            break
    if not win:
        print("\n".join(stages[0: wrong]))
        print("Казнь состоялась! Загаданное слово: {}.".format(word))


f = open('word_rus.txt')

for i in range(randint(1, 33723)):
    word = f.readline()

hangman(word.upper().rstrip())

