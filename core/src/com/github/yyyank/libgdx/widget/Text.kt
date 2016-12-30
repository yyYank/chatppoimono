package com.github.yyyank.libgdx.widget

/**
 * Created by yy_yank on 2016/11/01.
 */
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.github.yyyank.libgdx.domain.Position


class Text(val value: String, val fontSize: Int, val pos: Position, val rgba: Color = Color.BLACK) : Actor() {


    val font: BitmapFont
    val upperAlphabet: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ"
    val lowerAlphabet: String = "abcdefghijklmnopqrstuvwxyzａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ"
    val kanji: String = "得点審議中名入退室"
    val hiragana: String = "あいうえお" + "かきくけこ" + "さしすせそ" + "たちつてと" +
            "なにぬねの" + "はひふへほ" + "まみむめも" + "やゆよ" + "らりるれろ" + "わをん" +
            "がぎぐげご" + "ざじずぜぞ" + "だぢづでど" + "ばびぶべぼ" +
            "ぱぴぷぺぽ" + "ぁぃぅぇぉゃゅょ"
    val katakana: String = "アイウエオ" + "カキクケコ" +
            "サシスセソ" + "タチツテト" + "ナニヌネノ" + "ハヒフヘホ" +
            "マミムメモ" + "ヤユヨ" + "ラリルレロ" + "ワヲン" +
            "ガギグゲゴ" + "ザジズゼゾ" + "ダヂヅデド" + "バビブベボ" +
            "パピプペポ" + "ヤユヨ" + "ァィゥェォャュョ"
    val numeric: String = "1234567890"
    val symbol = "!?！？・ー[]"
    val characters = "$upperAlphabet$lowerAlphabet$hiragana$katakana$kanji$numeric$symbol"


    init {
        val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("font/NotoSansCJKjp-Regular.otf"))
        val param = FreeTypeFontGenerator.FreeTypeFontParameter()
        param.size = fontSize
        param.characters = characters
        font = fontGenerator.generateFont(param)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        font.setColor(rgba.r, rgba.g, rgba.b, rgba.a)
        font.draw(batch, value, pos.x, pos.y);
    }
}

