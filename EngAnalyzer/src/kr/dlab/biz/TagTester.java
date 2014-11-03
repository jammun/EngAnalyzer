package kr.dlab.biz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class TagTester {

	public static void main(String[] args) {
		
//		tagTest();
		tTest();
		
	}
	
	private static void tTest() {
		
/*		String[] a = new String[6];
		String[] b = new String[6];
		
		a[0] = "a";
		a[1] = "NN";
		a[2] = "냉장고";
		a[3] = "b";
		a[4] = "NNG";
		a[5] = "모바일";
		
		b[0] = "b";
		b[1] = "C1_P2";
		b[2] = "3";
		b[3] = "a";
		b[4] = "C2_P1";
		b[5] = "모바일";
		
		Object[] merged = ArrayUtils.addAll(a, b);
		
		for ( Object o : merged ) {
			System.out.println(o.toString());	
		}*/
		
		String s1 = "민원/NNG 제품/NNG 품질/NNG 불만/NNG 통화/NNG 내용/NNG 업그레이드/NNG 잘못/NNG 으/NNG 확인/NNG 안/MAG 해보/VV 업그레이드/NNG 하/VV 핸드폰/NNG 같/VA 경우/NNG 다/NNG 안/NNG 어/NNG 전부/MAG 안되/VV 통화/NNG 때/NNG 말/NNG 끊기/VV 카메라/NNG 안되/VV 뭐/NNG 없/VA 전화/NNG 일/NNG 을/NNG 사람/NNG 얼마나/MAG 답답/XR 안/VV";
		String s3 = "부품/NNG 품절/NNG 통화/NNG 내용/NNG 내용/NNG 부족/NNG 상담원/NNG 응대/NNG 입력/NNG 센터/NNG 폰/NNG 키/NNG 판/NNG 교체/NNG 맡기/VV 뒤/NNG 수리/NNG 가능/XR 하/VV 오늘/NNG 전화/NNG 오/VV 부품/NNG 입/VV 안/NNG 날/NNG 가능/XR 전화/NNG 오/VV 제품/NNG 나오/VV 안/NNG 부품/NNG 없/VA 수리/NNG 안되/VV 하/VV 있/VV 당장/NNG 내일/NNG 고치/VV 달라/VX 임대/NNG 폰/NNG LTE/NNG 주/VX 안되/VV 날/NNG 확실/XR 수리/NNG 가능/XR 확답/NNG 달라/NNP 수리/NNG 안되/VV 환불/NNG 주/VX 있/VV 죄송/XR 끝/NNG 날/NNG 안되/VV 때/NNG 어떻/VA 하/VV 주/VX 확답/NNG 달라/NNP";
		String s2 = "교환/NNG Wifi/NNG 연결/NNG 안되/VV 통화/NNG 내용/NNG lg/SL 기기/NNG 원래/NNG 안좋/VV 가요/NNG 지금/MAG 바꾸/VV 오/VX 폰/NNG 아직/MAG a/SL s/SL 안/MAG 가/VV Wifi/NNG 앞/NNG 전/NNG 경우/NNG Wifi/NNG 되/VV 애/NNG Wifi/NNG 잘/MAG 안되/VV 이/NNP 폰/NNG 바꾸/VV 얼마/NNG 안되/VV 가/VV 하/VV 지겹/VA 안/NNG 말/VX 삼성/NNP 구매/NNG 하/VV 이런/MM 없/VA lg/SL 구매/NNG 하/VV 나서/VV 계속/NNG 병원/NNG 구매/NNG 하/VV 사용/NNG 별로/MAG 안되/VV 확/MAG 무르/VV 버리/VX 싶/VX 그렇/VA 하/VV 없/VA 정말/MAG 가/VV 하/VV 정말로/MAG 아주/MAG 미치/VV 버리/VX 이렇/VA 경우/NNG 해지/NNG 자체/NNG 안되/VV 말/NNG";
		String s4 = "후/NNG 통화/NNG 끊기/VV sk/SL 사용자/NNG 이/VV 업그레이드/NNG 후/NNG 통화/NNG 끊기/VV 현상/NNG 자주/MAG 발생/NNG 현재/MAG 퀵커버/NNG 사용중/NNG 소프트웨어/NNG 버그/NNG 알/VV 퀵커버/NNG 오/VV 연관/NNG 있/VV 퀵커버/NNG 사용/NNG 않/VX 괜찮/VA 문제/NNG 발생/NNG 차후/NNG 업그레이드/NNG 되/VV 예정/NNG 얘기/NNG 다/MAG 어떻/VA 하/VV 하/VX 말/NNG 있/VV 답답/XR 전화기/NNG 제일/NNG 기본/NNG 통화/NNG 제대로/MAG 안되/VV 몇/MM 만원/NNG 요금/NNG 내/VV 사람/NNG 게/NNG 답변/NNG 같/VA 통화/NNG 끊기/VV 현상/NNG 있/VV 사람/NNG 다운/NNG 그레/NNG 들르/VV 해/NNG 하/VV 않/VX 괜찮/VA 방법/NNG 얘기/NNG 주/VX 되/VV 빠르/VA 답변/NNG 부탁/NNG 드리/VV 자사/NNG 홈페이지/NNG 확인/NNG 사항/NNG sw/SL 최신/NNG 버전/NNG 업그레이드/NNG 수정/NNG 되/VV 차후/NNG 보완/NNG 버전/NNG 오픈/NNG 되/VV 예정/NNG OS/NNG 다운/NNP 그/MM 레이/NNP 드/NNP 가능/XR";
		String s5 = "과실/NNG 비용/NNG 불만/NNG 통화/NNG 내용/NNG 서비스/NNG 센터/NNG 방문/NNG 수리/NNG 하/VV 아들/NNG 사용/NNG g/SL 고/XPN 사용/NNG 내년/NNG 폴/NNP 더/NNG 폰/NNG 바꾸/VV 사용/NNG 예정/NNG 떨어뜨리/VV 깨지/VV 서비스/NNG 센터/NNG 가/VV 수리/NNG 하/VV 마음/NNG 먹/VV 정도/NNG 예상/NNG 안/NNG 고장/NNG 나/VV 더/MAG 나오/VV 예상/NNG 하/VV 좀/MAG 저렴/XR 하/VV 달/VX 하/VV 하/VV 가/VV 기사/NNG 말/NNG 이/MM 제품/NNG 사용/NNG 말/NNG 정품/NNG 퀵커버/NNG 안/NNG 사용/NNG 고무/NNG 재질/NNG 되/VV 케이스/NNG 사용/NNG 하/VV 너무/MAG 화가/NNG 나/VV 그런/MM 제품/NNG 문제/NNG 있/VV 약하/VA 인지/NNG 있/VV 고객/NNG 한/MM 마디/NNG 안/NNG 납득/NNG 없/VA 도저히/MAG 수긍/NNG 없/VA 해당/NNG 부분/NNG 깨지/VV 터치/NNG 안되/VV 말/NNG 고객/NNG 고지/NNG 주/VX 아니/VA lg/SL 고객/NNG 씌우/VV 돈/NNG 센터/NNG 소리/NNG 지르/VV 하/VV 챙피/NNG 하/VV 언짢/VA 하/VV 나/VV 보상/NNG 받/VV 하/VX 돈/NNG 없/VA 그렇/VA 말이/NNG 예/NNG 다시/MAG lg/SL 제품/NNG 안/MAG 쓰/VV lg/SL 서비스/NNG 센터/NNG 기사/NNG 한/MM 말/NNG 다/MAG 글/NNG 올리/VV 예/NNG";
		String s8 = "민원/NNG 미디어/NNG 청취/NNG 줄이/VV 소음/NNG 크/VA 통화/NNG 내용/NNG 미디어/NNG 청취/NNG 제품/NNG 대하/VV 카/MAG 톡/MAG 문자/NNG 오/VV 소리/NNG 미디어/NNG 볼륨/NNG 해당/NNG 알리/VV 음/NNG 들리/VV 어떻/VA 이렇/VA 제품/NNG 만들/VV 사용/NNG 말/VX 볼륨/NNG 음/NNG 따로/MAG 작/VA 조절/NNG 있/VV 하/VV 주/VX 하/VV 카/NNG 톡/MAG 한/MM 오/VV 게/NNG 여러/MM 계속/MAG 오/VV 걸/VV 듣/VV 말/VX";
		String s6 = "퀵커버/NNG 서비스/NNG 교환/NNG 처리/NNG 절차/NNG 불만/NNG g/SL 스마트/NNG 폰/NNG 구매/NNG 몇/MM 되/VV 오늘/NNG 처음/NNG 서비스/NNG 을/NNG 받/VV 오/VX 엘지/NNP 전자/NNG 엘지/NNP 마크/NNP 붙이/VV 판매/NNG g/SL 케이스/NNG 서비스/NNG 센터/NNG 서/VV a/SL s/SL 안되/VV 못쓰/VV 물건/NNG 엘지/NNP 전자/NNG 상품/NNG 판매/NNG 때/NNG TV/NNG 광고/NNG 한/MM 케이스/NNG 보증/NNG 기간/NNG 서비스/NNG 센터/NNG 서비스/NNG 안되/VV 다른/MM 곳/NNG 알아보/VV 하/VV 하/VV 소비자/NNG 우롱/NNG 판매/NNG 광고/NNG 을/NNG 하/VV 책임/NNG 있/VX 제품/NNG 큼지막/XR 있/VV 말/NNG 서비스/NNG 기간/NNG 안되/VV 제품/NNG 자신/NNG 없/VA 싸/VV 팔/VV 비싸/VA 서비스/NNG 기간/NNG 다른/MM 제품/NNG 반/NNG 어처구니/NNG 없/VA 물건/NNG 팔/VV 때/NNG 각/MM 서비스/NNG 센터/NNG 물건/NNG 대하/VV 서비스/NNG 같이/MAG 제공/NNG 소비자/NNG 현옥/NNP lg/SL 마크/NNG 을/NNG 빼/VV 하/VV 바라/VV 정식/NNG 상품/NNG 대하/VV 민원/NNG 정부/NNG 부처/NNG 제기/NNG 하/VV 하/VX 자사/NNG 홈페이지/NNG e/SL dl/SL 접수/NNG 일시/NNG scs/SL 확인/NNG mc/SL 악세사리/NNG 상품/NNG 서비스/NNG 처리/NNG 방법/NNG 안내/NNG 김태호/NNP 게시/NNG 퀵커버/NNG 무상/NNG 보증/NNG 기간/NNG swap/SL 요청/NNG 고객/NNG 퀵커버/NNG 서비스/NNG 관련/NNG 내방/NNG 서비스/NNG 불가/NNG 다른/MM 곳/NNG 알아보/VV 하/VV 언급/NNG 처리/NNG 부서/NNG 회신/NNG 고객/NNG 안내/NNG 내용/NNG 회신/NNG 부탁/NNG 드리/VV";
		String s7 = "기본/NNG 애플리케이션/NNG 삭제/NNG 안되/VV 불만/NNG 개선/NNG 요구/NNG 업그레이드/NNG 알리/VV 창/NNG 관련/NNG 통화/NNG 내용/NNG g/SL 모델/NNG 사용/NNG 업그레이드/NNG 하/VV 뜨/VV 보/VV 키/NNG 캐/VV 업그레이드/NNG 같/VA 저/MM 안/NNG 싶/VX 엘지/NNP 핸드폰/NNG 이렇/VA 기본/NNG 깔리/VV 애플리케이션/NNG 많/VA 삭제/NNG 하/VV 싶/VX 삭제/NNG 안되/VV 찾/VV 힘들/VA 불편/NNG 죽/VV 메뉴/NNG 소프트/NNG 업그레이드/NNG 누르/VV 주/VX 누르/VV 업그레이드/NNG 바로/MAG 되/VV 자동/NNG 확인/NNG 체크/NNG 해제/NNG 하/VV 주/VX 되/VV 하/VV 알리/VV 창/NNG 계속/MAG 뜨/VV 뒤/NNG 내리/VV 지우/VV 하/VV 되/VV 계속/MAG 남/VV 있/VX 다른/MM 누르/VV 잘못/MAG 누르/VV 바로/MAG 업그레이드/NNG 되/VV 나중/NNG 버튼/NNG 나오/VV 지금/MAG 설치/NNG 창/NNG 확실/XR 키/NNG 캐/VV 문제/NNG 많/VA 안정/NNG 제대로/MAG 안되/VV 상태/NNG 올라오/VV 얘기/NNG 너무/MAG 많/VA 그냥/MAG 당분간/MAG 사용/NNG 안/NNG 쓰/VV 싶/VX 알리/VV 안/MAG 뜨/VV 방법/NNG 없/VA 사용자/NNG 의도/NNG 상관없이/MAG 하/VV 이/MM 제품/NNG 만들/VV 놓/VX 당연히/MAG 많이/MAG 불편/NNG 조금/NNG 잘못/MAG 누르/VV 업그레이드/NNG 버리/VX 키/NNG 캐/VV 업그레이드/NNG 하/VV 설정/NNG 상태/NNG 유지/NNG 되/VV 위/NNG 상단바/NNG 색깔/NNG 변하/VV 그런/MM 있/VV 자료/NNG 날아가/VV 않/VX 자료/NNG 안/MAG 날아가/VV 설정/NNG 다/MAG 다르/VA 애플리케이션/NNG 어떤/MM 꺼내/VV 놓/VX 벨소리/NNG 그런/MM 설정/NNG 귀찮/VA 안정/NNG 강/NNG 안되/VV 문제/NNG 많/VA 소리/NNG 엄청/MAG 많/VA 단점/NNG 많/VA 사용/NNG 필요/NNG 없/VA 알리/VV 안/MAG 없어지/VV 까딱/MAG 실수/NNG 그냥/MAG 눌리/VV 업그레이드/NNG 되/VV 버리/VX 폰/NNG 이렇/VA 죄송/XR 문제/NNG 해결/NNG 안/NNG 싶/VX 안/NNG 선택/NNG 주/VX 하/VX 업그레이드/NNG 해보/VV 키/NNG 캐/VV 안좋/VV 더/MAG 많/VA 상황/NNG 말/NNG 많/VA 엘지/NNP 다/MAG 알/VV 알리/VV 눌리/VV 지/VX 한번/NNG 더/MAG 창/NNG 뜸/NNG 지금/MAG 해보/VV 안되/VV 책임/NNG 지/VV 주/VX 나중/NNG 설치/NNG 누르/VV 나중/NNG 안/MAG 뜨/VV 나중/NNG 누르/VV 주/VX 이제/MAG 안/MAG 뜨/VV 알리/VV 안/MAG 뜨/VV 나중/NNG 뜰/NNG 안/MAG 뜨/VV 하/VV 어렵/VA 시간/NNG 나중/NNG 설치/NNG 하/VV 주/VX 하/VV 불편/NNG 앞/NNG 자동/NNG 확인/NNG 그런/MM 다/MAG 체크/NNG 안되/VV 있/VV 나중/NNG 뜨/VV 네/MM 빠지/VV 있/VV 뜨/VV 좀/MAG 안/MAG 뜨/VV 금/NNG 만들/VV 좋/VA 뭐/NNG 어렵/VA 기본/NNG 애플리케이션/NNG 너무/MAG 많이/MAG 깔리/VV 지우/VV 없/VA 쓰/VV 않/VX 너무/MAG 많이/MAG 깔리/VV 메모리/NNG 많이/MAG 잡아먹/VV 업그레이드/NNG 같/VA 자주/MAG 뜨/VV 불편/NNG 스마트/NNG 폰/NNG 받/VV 보/VX 제/XPN 조사/NNG 깔/VV 놓/VX 애플리케이션/NNG 있/VV 통신사/NNG 깔/VV 놓/VX 애플리케이션/NNG 있/VV 구/XPN 글/NNG 깔/VV 놓/VX 있/VV 아니/MAG 처음/NNG 부/NNG 무슨/MM 페이지/NNG 애플리케이션/NNG 예/NNG 쓰/VV 않/VX 태반/NNG 지우/VV 있/VV 없/VA 하/VV 그/MM 후/NNG as/SL 안되/VV 방법/NNG 없/VA 말/NNG 똑같/VA 말/NNG 일단/MAG 두/MM 올리/VV 주/VX 삭제/NNG 기본/NNG 업그레이드/NNG 자동/NNG 하/VV 뜨/VV 귀찮/VA 메모리/NNG 잡아먹/VV 짜증/NNG 나/VV 선택/NNG 없/VA 체크/NNG 다/MAG 빼/VV 불편/NNG";
		String s10 = "민원/NNG 고객/NNG 과실/NNG 제품/NNG 파손/NNG 통화/NNG 내용/NNG 제품/NNG 환불/NNG 하/VV 달/VX 차례/NNG 파손/NNG 메인보드/NNG 교체/NNG 기록/NNG 몇/MM 차례/NNG 센터/NNG 방문/NNG 하/VV 이/MM 기록/NNG 환불/NNG 가능/XR 소/XPN 보/NNG 안내/NNG 받/VV 환불/NNG 안/NNG 주/VX lg/SL 센터/NNG 앞/NNG 시위/NNG 하/VV 기준/NNG 대상/NNG 되/VV 않/VX 나/VV 꼭/MAG 환불/NNG 받/VV";
		String s9 = "통화/NNG 내용/NNG 교환/NNG 잘/MAG 되/VV 저/MM 핸드폰/NNG 다/MAG 환불/NNG 받/VV 싶/VX 지금/MAG 교환/NNG 한/NNP 달/NNG 안되/VV 알/VV 핸드폰/NNG 터치/NNG 잘/MAG 안되/VV 네/MM 버/NNP 사용/NNG 자꾸/MAG 멈추/VV 페이스/NNG 북/NNG 좋/VA 누르/VV 잘/MAG 안되/VV 가지/VV 몇/MM 일전/NNG 가/VV 이/MM 부분/NNG 업그레이드/NNG 한/MM 하/VV 나서/VV 계속/MAG 이런/MM 현상/NNG 있/VV 같/VA 하/VV 이렇/VA 불편/NNG 계속/MAG 쓰/VV 하/VV 이유/NNG 모르/VV 왜/MAG 이렇/VA 시간/NNG 뺏기/VV 매일/MAG 바꾸/VV 다니/VV 하/VV 모르/VV 오/VX 가/VV 하/VV 귀찮/VA 학교/NNG 다니/VV 버스/NNG 타/VV 다니/VV 바꾸/VV 하/VV 엄청/MAG 돈/NNG 많이/MAG 들/VV 그렇/VA 말/NNG 말/VX 환불/NNG 주/VX 기사/NNG 판단/NNG 있/VV 고객/NNG 지금/MAG 불편/NNG 사용/NNG 못하/VX 하/VV";

		ArrayList<TestArray> org1 = new ArrayList();
		ArrayList<TestArray> org2 = new ArrayList();
		
		ArrayList strResult1 = new ArrayList();
		
		String[] strArray1 = StringUtils.split(s1, " ");
		String[] strArray2 = StringUtils.split(s2, " ");
		String[] strArray3 = StringUtils.split(s3, " ");
		String[] strArray4 = StringUtils.split(s4, " ");
		String[] strArray5 = StringUtils.split(s5, " ");
		String[] strArray6 = StringUtils.split(s6, " ");
		String[] strArray7 = StringUtils.split(s7, " ");
		String[] strArray8 = StringUtils.split(s8, " ");
		String[] strArray9 = StringUtils.split(s9, " ");
		String[] strArray10 = StringUtils.split(s10, " ");
		
		ArrayList<String> arr1 = new ArrayList<String>(Arrays.asList(strArray1));
		ArrayList<String> arr2 = new ArrayList<String>(Arrays.asList(strArray2));
		ArrayList<String> arr3 = new ArrayList<String>(Arrays.asList(strArray3));
		ArrayList<String> arr4 = new ArrayList<String>(Arrays.asList(strArray4));
		ArrayList<String> arr5 = new ArrayList<String>(Arrays.asList(strArray5));
		ArrayList<String> arr6 = new ArrayList<String>(Arrays.asList(strArray6));
		ArrayList<String> arr7 = new ArrayList<String>(Arrays.asList(strArray7));
		ArrayList<String> arr8 = new ArrayList<String>(Arrays.asList(strArray8));
		ArrayList<String> arr9 = new ArrayList<String>(Arrays.asList(strArray9));
		ArrayList<String> arr10 = new ArrayList<String>(Arrays.asList(strArray10));
		
		TestArray t1_1 = new TestArray("1", arr1);
		TestArray t1_2 = new TestArray("2", arr2);
		TestArray t1_3 = new TestArray("3", arr3);
		TestArray t1_4 = new TestArray("4", arr4);
		TestArray t1_5 = new TestArray("5", arr5);
		TestArray t1_6 = new TestArray("6", arr6);
		TestArray t1_7 = new TestArray("7", arr7);
		TestArray t1_8 = new TestArray("8", arr8);
		TestArray t1_9 = new TestArray("9", arr9);
		TestArray t1_10 = new TestArray("10", arr10);
		
		ArrayList arr2_1 = new ArrayList();
		arr2_1.add("C1_P2");
		arr2_1.add("C1_P1");
		arr2_1.add("C1_P3");
		
		ArrayList arr2_2 = new ArrayList();
		arr2_2.add("C5_P2");
		arr2_2.add("C5_P1");
		arr2_2.add("C5_P3");
		
		ArrayList arr2_3 = new ArrayList();
		arr2_3.add("C3_P2");
		arr2_3.add("C3_P1");
		arr2_3.add("C3_P3");
		
		TestArray t2_1 = new TestArray("1", arr2_1);
		TestArray t2_2 = new TestArray("5", arr2_2);
		TestArray t2_3 = new TestArray("8", arr2_3);
		
		ArrayList finalArray1 = new ArrayList();
		finalArray1.add(t1_1);
		finalArray1.add(t1_2);
		finalArray1.add(t1_3);
		finalArray1.add(t1_4);
		finalArray1.add(t1_5);
		finalArray1.add(t1_6);
		finalArray1.add(t1_7);
		finalArray1.add(t1_8);
		finalArray1.add(t1_9);
		finalArray1.add(t1_10);
		
		ArrayList finalArray2 = new ArrayList();
		finalArray2.add(t2_1);
		finalArray2.add(t2_2);
		finalArray2.add(t2_3);
		
		HashSet<TestArray> set = new HashSet<TestArray>(finalArray1);
		boolean result = set.addAll(finalArray2);
		
		System.out.println("result = " + result);
		for ( int i = 0; i < finalArray1.size(); i++ ) {
			TestArray ta = (TestArray)finalArray1.get(i);
			System.out.println(ta.toString());			
		}
		for ( int i = 0; i < finalArray2.size(); i++ ) {	
			TestArray ta = (TestArray)finalArray2.get(i);
			System.out.println(ta.toString());
		}
		
	}
	
	
	
	private static void tagTest() {
		//문자열을 아래와 같이 입력합니다.
		String input = "A full day of use off a full charge and my battery is still over 70%. Light use, I've seen it in high 80% area. If I tether and use the camera a lot....I've seen it go as low as 58%. The battery life is superb.";
		
		//Stanford tagger 객체를 생성합니다.
		MaxentTagger tagger =  new MaxentTagger("models/english-left3words-distsim.tagger");

		//input String 을 PoS tagged String 으로 처리합니다.
		String tagged = tagger.tagString(input);
		System.out.println(tagged);
	}

}
