/*
 * # Radioボタンをトグルに変更し押下時に、押下したようにする
*/

$(function(){
	function ChangeColor($this, $event) {

        // SET UP
		// イベントの種類特定
		var $type = $event.target.type;
		
		// イベント発生以外の兄弟要素を取得
		var $sibling = $this.siblings();

        // EXECUTE
		// 「radioボタンか確認の上で処理を実施」
		if ($type == "radio") {
		
            // 押下箇所以外のclassを書き換え(非押下状態)
            $sibling.attr("class","btn btn-primary");

            // 押下箇所のclassを書き換え(押下状態)
            $this.attr("class","btn btn-primary active");
			
		} 
	}

    // EXECUTE_jquery
    $('#JPA input[type=radio]').change( function() {
        var $this = $(this).parent();
        ChangeColor($this, event);
    });

});

$(function(){

	// ウィンドウがscroll時にイベント発生
	$(window).on('scroll', function(){
		var scrollValue = $(this).scrollTop();
		$('.fixedmenu').trigger('customScroll', {posY: scrollValue});
	});

	$('.fixedmenu').each(function(){
		// メニューバーの高さを取得
		var $this=$(this);
		$this.data('initial', $this.offset().top);
	})
	.on('customScroll', function(event, object){
		var $this = $(this);
		if($this.hasClass('noresponsive') && mediaDetect('(max-width:600px)')){

		} else {
			// HEAERの領域を取得
			var offsetTop = 0;
			if($this.data('offsettop')) {
				offsetTop = $this.data('offsettop');
			}

			// メニューバーを固定するか判定する
			// メニューバーの位置 - メニューバーの高さ <= 現在のスクロール量
			if($this.data('initial') - offsetTop <= object.posY){
				// 要素を固定(class="fixedmenu"の位置にfixedを付ける)
				if(!$this.hasClass('fixed')){
					// グローバルナビゲーションが固定されると同時に、同じサイズのボックスを挿入して空白地帯を埋める
					var $substitute = $('<div></div>');
					$substitute.css({'margin':'0', 'padding':'0', 'font-size':'0', 'height':'0'})
								.addClass('substitute')
								.height($this.outerHeight(true))
							    .width($this.outerWidth(true));
					$this.after($substitute).addClass('fixed').css({top:offsetTop});
				}
			}else {
				//要素の固定を解除
				$this.next('.substitute').remove();
				$this.removeClass('fixed');
			}
		}
	});
});