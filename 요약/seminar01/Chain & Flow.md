# 01. Chain

## 📍 Chain이란?
> 동일 선상에 여러 View가 위치하게 될 때, View들이 공간을 공유하고 분할하는 방식을 제어하는 기능/제약(constraint)

<img src="https://developer.android.com/static/training/constraint-layout/images/constraint-chain_2x.png" width=50% height=50%/> <br>
- 2개 이상의 View들이 양방향으로 constraint가 연결되어 있는 형태
- LinearLayout의 weight와 유사하지만 더 많은 기능 제공

<br>

## 📍 Chain Style 유형
> Chain이 적용된 View에 속성 `layout_constraintHorizontal_chainStyle` 또는 `layout_constraintVertical_chainStyle`을 부여하여 설정
> - margin을 제외한 공간 분할

<img src="https://developer.android.com/static/training/constraint-layout/images/constraint-chain-styles_2x.png" width=50% height=50%/> <br>

### ① Spread (`spread`)
- 디폴트 값
- 모든 공간이 각 View에게 균등하게 분할됨

### ② Spread inside (`spread_inside`)
- 첫 번째와 마지막 View가 체인의 각 끝에 부착되고 이를 제외한 공간이 균등하게 분할됨

### ③ Weighted (`spread / spread_inside` + `match_constraint`)
- chainStyle 속성이 `spread` 또는 `spread_inside`로 설정되고, 특정 뷰의 크기가 0dp(match_constraint)로 설정된 경우
- 남은 공간을 match_constraint로 설정된 View로 가득 채움
- 속성 `layout_constraintHorizontal_weight` 또는 `layout_constraintVertical_weight`를 통해 가중치 부여 가능

### ④ Packed (`packed`)
- 모든 View들이 부착되며 *center*로 정렬됨
- 수직 또는 수평의 bias를 통해 위치 조정 가능

<br>

# 02. ConstraintLayout 2.0
- Google I/O 2019와 함께 ConstraintLayout 2.0.0의 베타 채널이 배포됨
- Flow라는 VirtualLayout 기능이 새롭게 제공됨
<br>

## 📍 VirtualLayout란?
> UI 계층에 포함되지 않으면서 다른 뷰들에 특정한 동작을 적용하며, 뷰의 위치를 정하는 과정을 간섭할 수 있는 컴포넌트
- constraint 설정의 복잡함 해결
- 평면적인 UI 구조 유지 가능
- 기존의 UI 시스템으로는 불가능한 다양한 기능 제공
- View의 위치에 대한 다양한 기능 캡슐화
- ConstraintHelper를 확장한 개념

<br>

## 📍 ConstraintHelper란?
> UI 계층에 포함되지 않으면서 다른 뷰들에 특정한 동작을 적용하는 컴포넌트
- View의 구조를 평면적으로 유지
- 하나의 View에 대해 여러 Helper 적용 가능
- View의 동작 캡슐화
- 예) ConstraintLayout 1.1의 *Guideline,* *Barrier*, *Group*

<br>

# 03. Flow
> 동일 선상에 위치한 위젯들의 Chain을 보다 쉽고 직관적으로 설정할 수 있는 VirtualLayout
- 뷰의 계층과 무관하게 작동
- Chain이 간소화된 개념
    - Chain 설정 여부를 직관적으로 확인 가능
    - Chain의 스타일을 직관적으로 설정 가능
<br>

## 📍 Attributes
- `pp:flow_wrapMode="none|chain|aligned"`
  - **none** : 단순히 참조하는 뷰들 사이에 Chain 생성
  - **chain** : 공간이 부족할 경우 여러 줄에 걸쳐서 View 배치
  - **aligned** : 공간이 부족할 경우 행과 열을 나눠 View 배치

- `android:orientation="horizontal|vertical`
  - Chain의 방향 설정
    
- `app:flow_verticalStyle="packed|spread|spread_inside"`
    
    `app:flow_horizontalStyle="packed|spread|spread_inside"`
  - Chain Style 설정
    
- `app:flow_verticalBias="float"`
    
    `app:flow_horizontalBias="float"`
  - Chain Style이 packed인 경우의 View 위치 조정
    
- `app:flow_verticalGap="dimension"`
    
    `app:flow_horizontalGap="dimension"`
  - View 사이의 Gap 설정
    
- `app:flow_horizontalAlign="start|end|center"`
    
    `app:flow_verticalAlign="top|bottom|center|baseline"`
  - View 정렬 방식

<br>

# 00. Reference
- [ConstraintLayout > Chains](https://constraintlayout.com/basics/create_chains.html)
- [Android Developers > Build a Responsive UI with ConstraintLayout](https://developer.android.com/develop/ui/views/layout/constraint-layout#constrain-chain)
- [Android Developers > ConstraintLayout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout#Chains)
- [Velog > Android Jetpack - ConstraintLayout 2.0 Part 1. Core Concepts](https://velog.io/@tura/android-jetpack-constraint-layout-2-part-1-core-concepts)
- [Android Developers > Flow](https://developer.android.com/reference/androidx/constraintlayout/helper/widget/Flow)
- [Velog > Android Jetpack - ConstraintLayout 2.0 Part 2. Flow](https://velog.io/@tura/constraint-layout-2-part-2-flow)
