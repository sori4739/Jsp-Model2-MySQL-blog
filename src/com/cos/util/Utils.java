package com.cos.util;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import com.cos.model.Board;

public class Utils {
	//파싱
	//미리 보기 내용을 세팅하기
	public static void setPreviewContent(List<Board> boards) {
		
		for(Board board : boards) {
			Document doc = Jsoup.parse(board.getContent());
			Elements ets = doc.select("img");
			if(ets != null) {
				for (Element et : ets) {
					et.remove();
				}
			}
			board.setContent(doc.toString());
		}
	}
	//미리 보기 이미지 세팅하기
	public static void setPreviewImg(List<Board> boards) {
	      for (Board board : boards) {
	         if (board.getContent()!=null) {
	            Document doc = Jsoup.parse(board.getContent());
	            Elements etyoutube = doc.select("a");
	            Element et = doc.selectFirst("img");
	            String thumbnail = "";
	            if (etyoutube != null) {
	               for (Element element : etyoutube) {
	                  String href = element.attr("href");
	                  String value = element.text();
	                  if (href.contains("https://www.youtube.com/watch") && !value.equals("")) {
	                     String video[] = href.split("=");
	                     String v = video[1];
	                     thumbnail = "http://i.ytimg.com/vi/" + v + "/0.jpg";
	                     board.setPreviewImg(thumbnail);
	                     break;
	                  }
	               }
	            }
	            if (thumbnail.equals("")) {
	               if (et != null) {
	                  String previewImg = et.attr("src");// 이미지 소스 찾기
	                  board.setPreviewImg(previewImg);
	               } else {
	                  board.setPreviewImg("/blog/img/home-blog/blog-1.jpg");
	               }
	            }
	         }else {
	            board.setPreviewImg("/blog/img/home-blog/blog-1.jpg");
	         }
	      }
	   }
	
	//유튜브 미리보기 세팅하기
	public static void setPreviewYoutube(Board board) {
		Document doc = Jsoup.parse(board.getContent());
		Elements ets = doc.select("a");
		if(ets != null) {
			for(Element et : ets) {
				String href = et.attr("href");
				
				if(href.contains("https://www.youtube.com/watch") && !et.text().equals("")) {
					String video[] = href.split("=");
					String v = video[1];
				
					String iframe = "<iframe src = 'https://www.youtube.com/embed/"+v+"' width='600px' height='350px' allowfullscreen=true></iframe>";
				
					et.after(iframe);
				
					board.setContent(doc.toString());
				}
			}
		}
	}
	
	
	//테스트
	@Test
	public void previewYoutubeTest() {
		String content ="<a href='https://www.youtube.com/watch?v=ScdQQfAjFTk'>https://www.youtube.com/watch?v=ScdQQfAjFTk</a>";
		Document doc = Jsoup.parse(content);
		Elements ets = doc.select("a");
		if(ets != null) {
			for(Element et : ets) {
				String href = et.attr("href");
				System.out.println(et.text());
				//System.out.println(href);
				if(href.contains("https://www.youtube.com/watch")) {
					String video[] = href.split("=");
					String v = video[1];
					//System.out.println(v);
					String iframe = "<iframe src = 'https://www.youtube.com/embed/"+v+"' width='600px' height='350px' allowfullscreen=true></iframe>";
					//System.out.println(iframe);
					et.after(iframe);
					//System.out.println(doc);
				}
			}
		}
	}//end of previewYoutubeTest
	
	public void previewContentTest() {
		String content ="<p>dsadsadsadasd</p>";
		
		Document doc = Jsoup.parse(content);
		System.out.println(doc);
		
		Elements ets = doc.select("img");
		System.out.println(ets.get(0));
		System.out.println(ets.get(1));
		
		if(ets != null) {
			for (Element et : ets) {
				et.remove();
			}
			System.out.println(doc);
		}
	}
}
