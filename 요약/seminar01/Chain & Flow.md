# 01. Chain

## ๐ Chain์ด๋?
> ๋์ผ ์ ์์ ์ฌ๋ฌ View๊ฐ ์์นํ๊ฒ ๋  ๋, View๋ค์ด ๊ณต๊ฐ์ ๊ณต์ ํ๊ณ  ๋ถํ ํ๋ ๋ฐฉ์์ ์ ์ดํ๋ ๊ธฐ๋ฅ/์ ์ฝ(constraint)

<img src="https://developer.android.com/static/training/constraint-layout/images/constraint-chain_2x.png" width=50% height=50%/> <br>
- 2๊ฐ ์ด์์ View๋ค์ด ์๋ฐฉํฅ์ผ๋ก constraint๊ฐ ์ฐ๊ฒฐ๋์ด ์๋ ํํ
- LinearLayout์ weight์ ์ ์ฌํ์ง๋ง ๋ ๋ง์ ๊ธฐ๋ฅ ์ ๊ณต

<br>

## ๐ Chain Style ์ ํ
> Chain์ด ์ ์ฉ๋ View์ ์์ฑ `layout_constraintHorizontal_chainStyle` ๋๋ `layout_constraintVertical_chainStyle`์ ๋ถ์ฌํ์ฌ ์ค์ 
> - margin์ ์ ์ธํ ๊ณต๊ฐ ๋ถํ 

<img src="https://developer.android.com/static/training/constraint-layout/images/constraint-chain-styles_2x.png" width=50% height=50%/> <br>

### โ  Spread (`spread`)
- ๋ํดํธ ๊ฐ
- ๋ชจ๋  ๊ณต๊ฐ์ด ๊ฐ View์๊ฒ ๊ท ๋ฑํ๊ฒ ๋ถํ ๋จ

### โก Spread inside (`spread_inside`)
- ์ฒซ ๋ฒ์งธ์ ๋ง์ง๋ง View๊ฐ ์ฒด์ธ์ ๊ฐ ๋์ ๋ถ์ฐฉ๋๊ณ  ์ด๋ฅผ ์ ์ธํ ๊ณต๊ฐ์ด ๊ท ๋ฑํ๊ฒ ๋ถํ ๋จ

### โข Weighted (`spread / spread_inside` + `match_constraint`)
- chainStyle ์์ฑ์ด `spread` ๋๋ `spread_inside`๋ก ์ค์ ๋๊ณ , ํน์  ๋ทฐ์ ํฌ๊ธฐ๊ฐ 0dp(match_constraint)๋ก ์ค์ ๋ ๊ฒฝ์ฐ
- ๋จ์ ๊ณต๊ฐ์ match_constraint๋ก ์ค์ ๋ View๋ก ๊ฐ๋ ์ฑ์
- ์์ฑ `layout_constraintHorizontal_weight` ๋๋ `layout_constraintVertical_weight`๋ฅผ ํตํด ๊ฐ์ค์น ๋ถ์ฌ ๊ฐ๋ฅ

### โฃ Packed (`packed`)
- ๋ชจ๋  View๋ค์ด ๋ถ์ฐฉ๋๋ฉฐ *center*๋ก ์ ๋ ฌ๋จ
- ์์ง ๋๋ ์ํ์ bias๋ฅผ ํตํด ์์น ์กฐ์  ๊ฐ๋ฅ

<br>

# 02. ConstraintLayout 2.0
- Google I/O 2019์ ํจ๊ป ConstraintLayout 2.0.0์ ๋ฒ ํ ์ฑ๋์ด ๋ฐฐํฌ๋จ
- Flow๋ผ๋ VirtualLayout ๊ธฐ๋ฅ์ด ์๋กญ๊ฒ ์ ๊ณต๋จ
<br>

## ๐ VirtualLayout๋?
> UI ๊ณ์ธต์ ํฌํจ๋์ง ์์ผ๋ฉด์ ๋ค๋ฅธ ๋ทฐ๋ค์ ํน์ ํ ๋์์ ์ ์ฉํ๋ฉฐ, ๋ทฐ์ ์์น๋ฅผ ์ ํ๋ ๊ณผ์ ์ ๊ฐ์ญํ  ์ ์๋ ์ปดํฌ๋ํธ
- constraint ์ค์ ์ ๋ณต์กํจ ํด๊ฒฐ
- ํ๋ฉด์ ์ธ UI ๊ตฌ์กฐ ์ ์ง ๊ฐ๋ฅ
- ๊ธฐ์กด์ UI ์์คํ์ผ๋ก๋ ๋ถ๊ฐ๋ฅํ ๋ค์ํ ๊ธฐ๋ฅ ์ ๊ณต
- View์ ์์น์ ๋ํ ๋ค์ํ ๊ธฐ๋ฅ ์บก์ํ
- ConstraintHelper๋ฅผ ํ์ฅํ ๊ฐ๋

<br>

## ๐ ConstraintHelper๋?
> UI ๊ณ์ธต์ ํฌํจ๋์ง ์์ผ๋ฉด์ ๋ค๋ฅธ ๋ทฐ๋ค์ ํน์ ํ ๋์์ ์ ์ฉํ๋ ์ปดํฌ๋ํธ
- View์ ๊ตฌ์กฐ๋ฅผ ํ๋ฉด์ ์ผ๋ก ์ ์ง
- ํ๋์ View์ ๋ํด ์ฌ๋ฌ Helper ์ ์ฉ ๊ฐ๋ฅ
- View์ ๋์ ์บก์ํ
- ์) ConstraintLayout 1.1์ย *Guideline,*ย *Barrier*,ย *Group*

<br>

# 03. Flow
> ๋์ผ ์ ์์ ์์นํ ์์ ฏ๋ค์ Chain์ ๋ณด๋ค ์ฝ๊ณ  ์ง๊ด์ ์ผ๋ก ์ค์ ํ  ์ ์๋ VirtualLayout
- ๋ทฐ์ ๊ณ์ธต๊ณผ ๋ฌด๊ดํ๊ฒ ์๋
- Chain์ด ๊ฐ์ํ๋ ๊ฐ๋
    - Chain ์ค์  ์ฌ๋ถ๋ฅผ ์ง๊ด์ ์ผ๋ก ํ์ธ ๊ฐ๋ฅ
    - Chain์ ์คํ์ผ์ ์ง๊ด์ ์ผ๋ก ์ค์  ๊ฐ๋ฅ
<br>

## ๐ Attributes
- `pp:flow_wrapMode="none|chain|aligned"`
  - **none** : ๋จ์ํ ์ฐธ์กฐํ๋ ๋ทฐ๋ค ์ฌ์ด์ Chain ์์ฑ
  - **chain** : ๊ณต๊ฐ์ด ๋ถ์กฑํ  ๊ฒฝ์ฐ ์ฌ๋ฌ ์ค์ ๊ฑธ์ณ์ View ๋ฐฐ์น
  - **aligned** : ๊ณต๊ฐ์ด ๋ถ์กฑํ  ๊ฒฝ์ฐ ํ๊ณผ ์ด์ ๋๋  View ๋ฐฐ์น

- `android:orientation="horizontal|vertical`
  - Chain์ ๋ฐฉํฅ ์ค์ 
    
- `app:flow_verticalStyle="packed|spread|spread_inside"`
    
    `app:flow_horizontalStyle="packed|spread|spread_inside"`
  - Chain Style ์ค์ 
    
- `app:flow_verticalBias="float"`
    
    `app:flow_horizontalBias="float"`
  - Chain Style์ด packed์ธ ๊ฒฝ์ฐ์ View ์์น ์กฐ์ 
    
- `app:flow_verticalGap="dimension"`
    
    `app:flow_horizontalGap="dimension"`
  - View ์ฌ์ด์ Gap ์ค์ 
    
- `app:flow_horizontalAlign="start|end|center"`
    
    `app:flow_verticalAlign="top|bottom|center|baseline"`
  - View ์ ๋ ฌ ๋ฐฉ์

<br>

# 00. Reference
- [ConstraintLayout > Chains](https://constraintlayout.com/basics/create_chains.html)
- [Android Developers > Build a Responsive UI with ConstraintLayout](https://developer.android.com/develop/ui/views/layout/constraint-layout#constrain-chain)
- [Android Developers > ConstraintLayout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout#Chains)
- [Velog > Android Jetpack - ConstraintLayout 2.0 Part 1. Core Concepts](https://velog.io/@tura/android-jetpack-constraint-layout-2-part-1-core-concepts)
- [Android Developers > Flow](https://developer.android.com/reference/androidx/constraintlayout/helper/widget/Flow)
- [Velog > Android Jetpack - ConstraintLayout 2.0 Part 2. Flow](https://velog.io/@tura/constraint-layout-2-part-2-flow)
