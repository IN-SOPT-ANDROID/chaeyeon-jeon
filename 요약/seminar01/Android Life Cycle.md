# 01. Android Activity Life Cycle

## π μλͺ μ£ΌκΈ°(Life Cycle)Β·μλͺ μ£ΌκΈ°λ?
> νλ‘μΈμ€μ μμ± λ° μλ©Έμ ν¬ν¨ν μΌλ ¨μ κ³Όμ 
- μλλ‘μ΄λλ μ±μ΄ μ€νλ ν μν λ³νκ° λ°μν  λλ§λ€ νλ©΄μ λ³΄μ¬μ§λ μ‘ν°λΉν°μ **μλͺ μ£ΌκΈ° λ©μλ**λ₯Ό νΈμΆνμ¬ μν λ³νλ₯Ό μλ¦Ό
- λͺ¨λ°μΌ μ»΄ν¨νμ μμμ νμ λμμΌλ νμ(ex. μ ν, λμμ μ¬μ)λ‘ μνν΄μΌ νλ μμ μ‘΄μ¬
  - μλλ‘μ΄λλ μμ€ν μμμ΄ λΆμ‘±νλ©΄ μ¬μ©μ λμ μμ΄ νλ‘μΈμ€ κ°μ  μ’λ£
  - μλͺμ£ΌκΈ° λ©μλλ₯Ό ν΅ν΄ λ°°ν°λ¦¬, λ€νΈμν¬ νΈλν½ λ±μ μμ μ μ½ κ°λ₯
<br>

## π μλͺ μ£ΌκΈ° λ©μλ
```
β οΈ μλͺ μ£ΌκΈ° λ©μλλ μν λ³νμ λ°λΌ μλλ‘μ΄λκ° νΈμΆνκΈ° λλ¬Έμ μ§μ  νΈμΆ λΆκ°λ₯
```
### β  onCreate()
```
override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  // μ‘ν°λΉν° μμ±
}
```
- **`Bundle` νμμ `savedInstanceState` κ°μ²΄** : μ‘ν°λΉν°κ° μ€μ§ Β· μ μ§λλ©΄ μμμ μΌλ‘ μ‘ν°λΉν° μνλ₯Ό μ μ₯νκ³ , μ‘ν°λΉν°κ° λ³΅κ· Β· μ¬μ€νλ  λ μ μ₯λ λ°μ΄ν°λ₯Ό ν΅ν΄ μλ μνλ‘ λ³΅κ·
    - μ‘ν°λΉν°λ₯Ό μ²μ μμνλ κ²½μ°μλ null κ° ν¬ν¨
- νμμ μΌλ‘ κ΅¬νν΄μΌ ν¨
- `finish()` νΈμΆ μ `onDestroy()` νΈμΆ

### β‘ onStart()
```
override fun onStart() {
  super.onStart()
  // μ‘ν°λΉν° λ³΄μ΄κΈ° μμ
}
```
- `finish()` νΈμΆ μ `onStop()` νΈμΆ

### β’ onResume()
```
override fun onResume() {
  super.onResume()
  // μ‘ν°λΉν°κ° λ³΄μ΄λ©° μ¬μ©μμ μνΈμμ©μ΄ κ°λ₯ν μν
}
```
- `finish()` νΈμΆ μ `onPause()` νΈμΆ

### β£ onPause()
```
override fun onPause() {
  super.onPause()
  // μ‘ν°λΉν° μΌλΆ κ°λ €μ§
}
```
- μ‘ν°λΉν°κ° foregroundμ μ‘΄μ¬νμ§ μκ³  backgroundμ ν΄λΉ
- μ§§κ² μ§νλμ΄μΌ νλ ν¨μμ΄κΈ° λλ¬Έμ λΆνκ° ν° μμ μ§μ

### β€ onStop()
```
override fun onStop() {
  super.onStop()
  // μ‘ν°λΉν° μμ ν κ°λ €μ§
}
```
- ν¬λͺν μλΈ μ‘ν°λΉν°κ° νΈμΆλ κ²½μ°μλ λ©μΈ μ‘ν°λΉν°κ° λ³΄μ΄κΈ° λλ¬Έμ νΈμΆλμ§ μμ
- μμ μμλ `onRestart()`, μ’λ£ μμλ `onDestroy()` νΈμΆ
- κ°μ  μ’λ£ μ νΈμΆλμ§ μκΈ° λλ¬Έμ, κ°μ  μ’λ£ μ μ μνλμ΄μΌ νλ ν¨μλ μ μ μν

### β₯ onDestroy()
```
override fun onDestroy() {
  super.onDestroy()
  // μ‘ν°λΉν° μ’λ£
}
```

### β¦ onRestart()
```
override fun onRestart() {
  super.onRestart()
  // μμ ν κ°λ €μ‘λ μ‘ν°λΉν° λ€μ μ€ν
}
```
- μ‘ν°λΉν° μμ± X
<br>

## π μ‘ν°λΉν°μ μν μ²μ΄
![activity_lifecycle](https://user-images.githubusercontent.com/70993562/194457882-cb7d16f3-b6dc-425d-b381-c58c3ddb7a6d.png)
- Activity Running κΈ°μ€ μν λμΉ­ κ΅¬μ‘°λ₯Ό μ΄λ£¨κ³  μμ!
<br>

## π μ‘ν°λΉν°μ κ°μ  μ’λ£μ μν λ³΅μ

### π‘ μλͺμ£ΌκΈ° λ©μλμ μν μν λ³΅μμ λ¬Έμ μ 
- μμ λΆμ‘±, νλ©΄ λ°©ν₯ μ ν λ±μ μ΄μ λ‘ μμ€νμ΄ μ‘ν°λΉν°λ₯Ό νκΈ°ν  μ μμ
    - μ‘ν°λΉν° λ³΅μμ΄ λΆκ°λ₯
    - μ‘ν°λΉν° μ€ν μ μμ€νμ΄ μ‘ν°λΉν° μΈμ€ν΄μ€ μ¬μμ±
- μ¬μ©μλ μμ€νμ μν μ‘ν°λΉν° νκΈ° Β· λ³΅μ μ¬λΆλ₯Ό μ μ μμ
- κ°μ  μ’λ£μ κ²½μ°μλ μ‘ν°λΉν° μνλ₯Ό μ μ₯ν  μ μλλ‘ μλ λ©μλ μ κ³΅
    - μ¬μ©μκ° μ§μ  μ¬μ μνμ¬ μ¬μ©

### β  onSaveInstanceState()
> μ‘ν°λΉν°κ° λ³΄μ΄λ λ§μ§λ§ μμ μ νΈμΆλμ΄ μν μ μ₯
```
override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
  super.onSaveInstanceState(outState, outPersistentState)
}
```
- onStart() μ΄νμ νΈμΆλμ΄ onCreate() λ΄λΆμμ Activityλ₯Ό λ³΅μνλ κ²½μ° Bundle κ°μ²΄κ° λΉμ΄μμ μ μμ
    - onCreate() λ΄λΆμμ μν λ³΅μ μμλ Bundle κ°μ²΄κ° null κ°μΈμ§ νμΈ
    - onRestoreInstanceState() μ¬μ μ

### β‘ onRestoreInstanceState()
> μ‘ν°λΉν°κ° λ€μ μμλλ μμ μ νΈμΆλμ΄ μν λ³΅μ
```
override fun onRestoreInstanceState(savedInstanceState: Bundle) {
  super.onRestoreInstanceState(savedInstanceState)
}
```
![restore_instance](https://user-images.githubusercontent.com/70993562/194458850-6b067f7a-7bd6-48db-be43-26a0c547157d.png)

<img src="https://user-images.githubusercontent.com/70993562/194458906-a5da6c26-d7d2-4332-acae-1058a8bca32b.png" width=50% height=50%/> <br>
β² onSavedInstanceState(), onRestoreInstanceState()μ νΈμΆ μμΉλ₯Ό λνλΈ κ·Έλ¦Ό

<img src="https://user-images.githubusercontent.com/70993562/194459649-31fd5f50-db24-4b89-a908-4f8cbb302793.png" width=50% height=50%/> <br>
β² API 28+ λΆν°λ onSaveInstanceState()κ° onStop() λ€μμ νΈμΆλλ€κ³  ν¨

<br>

## π μ‘ν°λΉν° μλͺ μ£ΌκΈ° μμ©

### νλ‘μ νΈ μ€ν
- onCreate() β onStart() β onResume()

### νλ©΄ λ°©ν₯ μ ν
- onPause() β onStop() β **onDestroy()** β onCreate() β onStart() β onResume()

### ν λ²νΌ ν΄λ¦­
- onPause() β onStop()

### Back λ²νΌμΌλ‘ λ³΅κ·
- onRestart() β onStart() β onResume()

### νλ‘μ νΈ μ’λ£
- onPause() β onStop() β onDestroy()

### λ€λ₯Έ μ‘ν°λΉν° μ΄λ (A β B)
- A.onPause() β B.onCreate() β B.onStart() β B.onResume() β A.onStop()

### λ€λ₯Έ μ‘ν°λΉν°μμ λ³΅κ· (B β A)
- B.onPause() β A.onRestart() β A.Start() β A.onResume() β B.onStop() β B.onDestroy()

### ν¬λͺ μ‘ν°λΉν°λ‘ μ΄λ (A β B)
- A.onPause() β B.onCreate() β B.onStart() β B.onResume()

### ν¬λͺ μ‘ν°λΉν°μμ λ³΅κ· (B β A)
- B.onPause() β A.onResume() β B.onStop() β A.onDestroy()

### Back λ²νΌμΌλ‘ μ‘ν°λΉν° μ’λ£
- onPause() β onStop() β onDestroy()

# 02. Android Fragment Life Cycle
![fragment-view-lifecycle](https://user-images.githubusercontent.com/70993562/194460723-11bca3d9-8c6f-48c3-b023-47a2135cf7f0.png)

# 00. Reference
- [Android Developers > The activity lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle)
- [Android Developers > Fragment lifecycle](https://developer.android.com/guide/fragments/lifecycle)
- [Android Developers > Handling Lifecycles with Lifecycle-Aware Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
