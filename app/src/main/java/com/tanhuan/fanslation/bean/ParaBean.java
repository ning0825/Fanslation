package com.tanhuan.fanslation.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ParaBean implements Serializable {
    //todo change to Parcelable and their difference

    /**
     * blng_sents_part : {"more":"blng_sents","sentence-count":5,"sentence-pair":[{"aligned-words":{"src":{"chars":[{"@e":"3","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]},"q":[{"@e":"17","@s":"11"}]},{"@e":"6","@id":"1","@s":"4","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"17","@id":"2","@s":"11","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]},"q":[{"@e":"17","@s":"11"}]},{"@e":"24","@id":"3","@s":"18","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"3"}]}}]},"tran":{"chars":[{"@e":"2","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"3","@id":"1","@s":"2","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"10","@id":"2","@s":"8","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]}},{"@e":"11","@id":"3","@s":"10","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"3"}]}}]}},"sentence":"It is the thrill of the chase.","sentence-eng":"It is the <b>thrill<\/b> of the chase.","sentence-speech":"It+is+the+thrill+of+the+chase.&le=eng","sentence-translation":"它是一生中令人激动的时刻。","speech-size":"8kb","url":"http://danci.911cha.com/it%252FThrill.html"},{"aligned-words":{"src":{"chars":[{"@e":"7","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"17","@id":"1","@s":"8","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"21","@id":"2","@s":"18","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]}},{"@e":"37","@id":"3","@s":"31","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"6"}]},"q":[{"@e":"37","@s":"31"}]},{"@e":"40","@id":"4","@s":"38","aligns":{"sc":[{"@id":"4"}],"tc":[{"@id":"5"}]}},{"@e":"48","@id":"5","@s":"41","aligns":{"sc":[{"@id":"5"}],"tc":[{"@id":"3"}]}},{"@e":"52","@id":"6","@s":"49","aligns":{"sc":[{"@id":"6"}],"tc":[{"@id":"4"}]}}]},"tran":{"chars":[{"@e":"4","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"5","@id":"1","@s":"4","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"7","@id":"2","@s":"6","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]}},{"@e":"12","@id":"3","@s":"10","aligns":{"sc":[{"@id":"5"}],"tc":[{"@id":"3"}]}},{"@e":"16","@id":"4","@s":"14","aligns":{"sc":[{"@id":"6"}],"tc":[{"@id":"4"}]}},{"@e":"17","@id":"5","@s":"16","aligns":{"sc":[{"@id":"4"}],"tc":[{"@id":"5"}]}},{"@e":"19","@id":"6","@s":"17","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"6"}]}}]}},"sentence":"Hester looked at him with the thrill of another joy.","sentence-eng":"Hester looked at him with the <b>thrill<\/b> of another joy.","sentence-speech":"Hester+looked+at+him+with+the+thrill+of+another+joy.&le=eng","sentence-translation":"海丝特望着他，心头又是一阵喜悦的震颤。","speech-size":"15kb","url":"http://www.ebigear.com/news-196-27264.html"},{"aligned-words":{"src":{"chars":[{"@e":"5","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"15","@id":"1","@s":"6","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"21","@id":"2","@s":"16","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"4"}]}},{"@e":"28","@id":"3","@s":"22","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"5"}]}},{"@e":"35","@id":"4","@s":"29","aligns":{"sc":[{"@id":"4"}],"tc":[{"@id":"3"}]}},{"@e":"41","@id":"5","@s":"36","aligns":{"sc":[{"@id":"5"}],"tc":[{"@id":"2"}]}},{"@e":"51","@id":"6","@s":"43","aligns":{"sc":[{"@id":"6"}],"tc":[{"@id":"6"}]}},{"@e":"55","@id":"7","@s":"52","aligns":{"sc":[{"@id":"7"}],"tc":[{"@id":"7"}]}},{"@e":"64","@id":"8","@s":"56","aligns":{"sc":[{"@id":"8"}],"tc":[{"@id":"8"}]}},{"@e":"70","@id":"9","@s":"65","aligns":{"sc":[{"@id":"9"}],"tc":[{"@id":"9"}]}},{"@e":"75","@id":"10","@s":"72","aligns":{"sc":[{"@id":"10"}],"tc":[{"@id":"10"}]}},{"@e":"79","@id":"11","@s":"76","aligns":{"sc":[{"@id":"11"}],"tc":[{"@id":"11"}]}},{"@e":"90","@id":"12","@s":"84","aligns":{"sc":[{"@id":"12"}],"tc":[{"@id":"12"}]},"q":[{"@e":"90","@s":"84"}]},{"@e":"93","@id":"13","@s":"91","aligns":{"sc":[{"@id":"13"}],"tc":[{"@id":"13"}]}},{"@e":"103","@id":"14","@s":"94","aligns":{"sc":[{"@id":"14"}],"tc":[{"@id":"14"}]}},{"@e":"106","@id":"15","@s":"104","aligns":{"sc":[{"@id":"15"}],"tc":[{"@id":"15"}]}},{"@e":"114","@id":"16","@s":"107","aligns":{"sc":[{"@id":"16"}],"tc":[{"@id":"18"}]}},{"@e":"120","@id":"17","@s":"115","aligns":{"sc":[{"@id":"17"}],"tc":[{"@id":"19"}]}},{"@e":"124","@id":"18","@s":"121","aligns":{"sc":[{"@id":"18"}],"tc":[{"@id":"16"}]}},{"@e":"132","@id":"19","@s":"125","aligns":{"sc":[{"@id":"19"}],"tc":[{"@id":"17"}]}},{"@e":"135","@id":"20","@s":"133","aligns":{"sc":[{"@id":"20"}],"tc":[{"@id":"20"}]}},{"@e":"144","@id":"21","@s":"136","aligns":{"sc":[{"@id":"21"}],"tc":[{"@id":"23"}]}},{"@e":"150","@id":"22","@s":"145","aligns":{"sc":[{"@id":"22"}],"tc":[{"@id":"21"}]}},{"@e":"166","@id":"23","@s":"151","aligns":{"sc":[{"@id":"23"}],"tc":[{"@id":"22"}]}}]},"tran":{"chars":[{"@e":"3","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"5","@id":"1","@s":"3","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"7","@id":"2","@s":"5","aligns":{"sc":[{"@id":"5"}],"tc":[{"@id":"2"}]}},{"@e":"8","@id":"3","@s":"7","aligns":{"sc":[{"@id":"4"}],"tc":[{"@id":"3"}]}},{"@e":"10","@id":"4","@s":"8","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"4"}]}},{"@e":"12","@id":"5","@s":"10","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"5"}]}},{"@e":"14","@id":"6","@s":"13","aligns":{"sc":[{"@id":"6"}],"tc":[{"@id":"6"}]}},{"@e":"15","@id":"7","@s":"14","aligns":{"sc":[{"@id":"7"}],"tc":[{"@id":"7"}]}},{"@e":"17","@id":"8","@s":"15","aligns":{"sc":[{"@id":"8"}],"tc":[{"@id":"8"}]}},{"@e":"21","@id":"9","@s":"17","aligns":{"sc":[{"@id":"9"}],"tc":[{"@id":"9"}]}},{"@e":"24","@id":"10","@s":"22","aligns":{"sc":[{"@id":"10"}],"tc":[{"@id":"10"}]}},{"@e":"26","@id":"11","@s":"24","aligns":{"sc":[{"@id":"11"}],"tc":[{"@id":"11"}]}},{"@e":"30","@id":"12","@s":"26","aligns":{"sc":[{"@id":"12"}],"tc":[{"@id":"12"}]}},{"@e":"31","@id":"13","@s":"30","aligns":{"sc":[{"@id":"13"}],"tc":[{"@id":"13"}]}},{"@e":"33","@id":"14","@s":"31","aligns":{"sc":[{"@id":"14"}],"tc":[{"@id":"14"}]}},{"@e":"34","@id":"15","@s":"33","aligns":{"sc":[{"@id":"15"}],"tc":[{"@id":"15"}]}},{"@e":"37","@id":"16","@s":"34","aligns":{"sc":[{"@id":"18"}],"tc":[{"@id":"16"}]}},{"@e":"41","@id":"17","@s":"37","aligns":{"sc":[{"@id":"19"}],"tc":[{"@id":"17"}]}},{"@e":"43","@id":"18","@s":"41","aligns":{"sc":[{"@id":"16"}],"tc":[{"@id":"18"}]}},{"@e":"45","@id":"19","@s":"43","aligns":{"sc":[{"@id":"17"}],"tc":[{"@id":"19"}]}},{"@e":"46","@id":"20","@s":"45","aligns":{"sc":[{"@id":"20"}],"tc":[{"@id":"20"}]}},{"@e":"48","@id":"21","@s":"46","aligns":{"sc":[{"@id":"22"}],"tc":[{"@id":"21"}]}},{"@e":"50","@id":"22","@s":"48","aligns":{"sc":[{"@id":"23"}],"tc":[{"@id":"22"}]}},{"@e":"53","@id":"23","@s":"51","aligns":{"sc":[{"@id":"21"}],"tc":[{"@id":"23"}]}}]}},"sentence":"They come from every corner of the globe, most not for personal glory, but for the thrill of competing or raising money for charity or honoring their fellow citizens.","sentence-eng":"They come from every corner of the globe, most not for personal glory, but for the <b>thrill<\/b> of competing or raising money for charity or honoring their fellow citizens.","sentence-speech":"They+come+from+every+corner+of+the+globe%2C+most+not+for+personal+glory%2C+but+for+the+thrill+of+competing+or+raising+money+for+charity+or+honoring+their+fellow+citizens.&le=eng","sentence-translation":"他们来自地球的每个角落，不为个人荣誉而战，而是为了激动人心的赛事或为能为慈善事业募集资金及当地市民的荣誉。","speech-size":"48kb","url":"http://www.remword.cn/article-969-1.html"}]}
     * collins : {"collins_entries":[{"basic_entries":{"basic_entry":[{"cet":"CET4 TEM4","headword":"thrill","wordforms":{"wordform":[{"word":"thrilling"},{"word":"thrilled"},{"word":"thrills"}]}}]},"entries":{"entry":[{"tran_entry":[{"exam_sents":{"sent":[{"chn_sent":"我记得，在不知道圣诞节早上会收到何种礼物时自己的激动心情。","eng_sent":"I can remember the thrill of not knowing what I would get on Christmas morning."}]},"pos_entry":{"pos":"N-COUNT","pos_tips":"可数名词"},"tran":"If something gives you a <b>thrill<\/b>, it gives you a sudden feeling of great excitement, pleasure, or fear. (突然的) 激动; 狂喜; 恐惧"}]},{"tran_entry":[{"exam_sents":{"sent":[{"chn_sent":"这种紧张的气氛使他既害怕又激动。","eng_sent":"The electric atmosphere both terrified and thrilled him."}]},"pos_entry":{"pos":"V-T/V-I","pos_tips":"及物动词/不及物动词"},"tran":"If something <b>thrills<\/b> you, or if you <b>thrill<\/b> <b>at<\/b> it, it gives you a feeling of great pleasure and excitement. 使激动; 感到激动"}]},{"tran_entry":[{"seeAlsos":{"seeAlso":[{"seeword":"thrilled"},{"seeword":"thrilling"}],"seealso":"see also"}}]}]},"headword":"thrill","phonetic":"θrɪl","star":"2"}]}
     * ec : {"exam_type":["高中","IELTS","初中","CET6","GRE","商务英语","考研"],"source":{"name":"有道词典","url":"http://dict.youdao.com"},"word":[{"return-phrase":{"l":{"i":"thrill"}},"trs":[{"tr":[{"l":{"i":["n. 激动；震颤；紧张"]}}]},{"tr":[{"l":{"i":["vt. 使\u2026颤动；使\u2026紧张；使\u2026感到兴奋或激动"]}}]},{"tr":[{"l":{"i":["vi. 颤抖；感到兴奋；感到紧张"]}}]}],"ukphone":"θrɪl","ukspeech":"thrill&type=1","usphone":"θrɪl","usspeech":"thrill&type=2"}]}
     * input : thrill
     * lang : eng
     * le : en
     * meta : {"dicts":["blng_sents_part","collins","meta","simple","ec"],"guessLanguage":"eng","input":"thrill","lang":"eng","le":"en"}
     * simple : {"query":"thrill","word":[{"return-phrase":"thrill","ukphone":"θrɪl","ukspeech":"thrill&type=1","usphone":"θrɪl","usspeech":"thrill&type=2"}]}
     */

    private BlngSentsPartBean blng_sents_part;
    private CollinsBean collins;
    private EcBean ec;
    private String input;
    private String lang;
    private String le;
    private MetaBean meta;
    private SimpleBean simple;

    public BlngSentsPartBean getBlng_sents_part() {
        return blng_sents_part;
    }

    public void setBlng_sents_part(BlngSentsPartBean blng_sents_part) {
        this.blng_sents_part = blng_sents_part;
    }

    public CollinsBean getCollins() {
        return collins;
    }

    public void setCollins(CollinsBean collins) {
        this.collins = collins;
    }

    public EcBean getEc() {
        return ec;
    }

    public void setEc(EcBean ec) {
        this.ec = ec;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLe() {
        return le;
    }

    public void setLe(String le) {
        this.le = le;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public SimpleBean getSimple() {
        return simple;
    }

    public void setSimple(SimpleBean simple) {
        this.simple = simple;
    }

    public static class BlngSentsPartBean implements Serializable {
        /**
         * more : blng_sents
         * sentence-count : 5
         * sentence-pair : [{"aligned-words":{"src":{"chars":[{"@e":"3","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"6","@id":"1","@s":"4","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"17","@id":"2","@s":"11","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]},"q":[{"@e":"17","@s":"11"}]},{"@e":"24","@id":"3","@s":"18","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"3"}]}}]},"tran":{"chars":[{"@e":"2","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"3","@id":"1","@s":"2","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"10","@id":"2","@s":"8","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]}},{"@e":"11","@id":"3","@s":"10","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"3"}]}}]}},"sentence":"It is the thrill of the chase.","sentence-eng":"It is the <b>thrill<\/b> of the chase.","sentence-speech":"It+is+the+thrill+of+the+chase.&le=eng","sentence-translation":"它是一生中令人激动的时刻。","speech-size":"8kb","url":"http://danci.911cha.com/it%252FThrill.html"},{"aligned-words":{"src":{"chars":[{"@e":"7","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"17","@id":"1","@s":"8","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"21","@id":"2","@s":"18","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]}},{"@e":"37","@id":"3","@s":"31","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"6"}]},"q":[{"@e":"37","@s":"31"}]},{"@e":"40","@id":"4","@s":"38","aligns":{"sc":[{"@id":"4"}],"tc":[{"@id":"5"}]}},{"@e":"48","@id":"5","@s":"41","aligns":{"sc":[{"@id":"5"}],"tc":[{"@id":"3"}]}},{"@e":"52","@id":"6","@s":"49","aligns":{"sc":[{"@id":"6"}],"tc":[{"@id":"4"}]}}]},"tran":{"chars":[{"@e":"4","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"5","@id":"1","@s":"4","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"7","@id":"2","@s":"6","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]}},{"@e":"12","@id":"3","@s":"10","aligns":{"sc":[{"@id":"5"}],"tc":[{"@id":"3"}]}},{"@e":"16","@id":"4","@s":"14","aligns":{"sc":[{"@id":"6"}],"tc":[{"@id":"4"}]}},{"@e":"17","@id":"5","@s":"16","aligns":{"sc":[{"@id":"4"}],"tc":[{"@id":"5"}]}},{"@e":"19","@id":"6","@s":"17","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"6"}]}}]}},"sentence":"Hester looked at him with the thrill of another joy.","sentence-eng":"Hester looked at him with the <b>thrill<\/b> of another joy.","sentence-speech":"Hester+looked+at+him+with+the+thrill+of+another+joy.&le=eng","sentence-translation":"海丝特望着他，心头又是一阵喜悦的震颤。","speech-size":"15kb","url":"http://www.ebigear.com/news-196-27264.html"},{"aligned-words":{"src":{"chars":[{"@e":"5","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"15","@id":"1","@s":"6","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"21","@id":"2","@s":"16","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"4"}]}},{"@e":"28","@id":"3","@s":"22","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"5"}]}},{"@e":"35","@id":"4","@s":"29","aligns":{"sc":[{"@id":"4"}],"tc":[{"@id":"3"}]}},{"@e":"41","@id":"5","@s":"36","aligns":{"sc":[{"@id":"5"}],"tc":[{"@id":"2"}]}},{"@e":"51","@id":"6","@s":"43","aligns":{"sc":[{"@id":"6"}],"tc":[{"@id":"6"}]}},{"@e":"55","@id":"7","@s":"52","aligns":{"sc":[{"@id":"7"}],"tc":[{"@id":"7"}]}},{"@e":"64","@id":"8","@s":"56","aligns":{"sc":[{"@id":"8"}],"tc":[{"@id":"8"}]}},{"@e":"70","@id":"9","@s":"65","aligns":{"sc":[{"@id":"9"}],"tc":[{"@id":"9"}]}},{"@e":"75","@id":"10","@s":"72","aligns":{"sc":[{"@id":"10"}],"tc":[{"@id":"10"}]}},{"@e":"79","@id":"11","@s":"76","aligns":{"sc":[{"@id":"11"}],"tc":[{"@id":"11"}]}},{"@e":"90","@id":"12","@s":"84","aligns":{"sc":[{"@id":"12"}],"tc":[{"@id":"12"}]},"q":[{"@e":"90","@s":"84"}]},{"@e":"93","@id":"13","@s":"91","aligns":{"sc":[{"@id":"13"}],"tc":[{"@id":"13"}]}},{"@e":"103","@id":"14","@s":"94","aligns":{"sc":[{"@id":"14"}],"tc":[{"@id":"14"}]}},{"@e":"106","@id":"15","@s":"104","aligns":{"sc":[{"@id":"15"}],"tc":[{"@id":"15"}]}},{"@e":"114","@id":"16","@s":"107","aligns":{"sc":[{"@id":"16"}],"tc":[{"@id":"18"}]}},{"@e":"120","@id":"17","@s":"115","aligns":{"sc":[{"@id":"17"}],"tc":[{"@id":"19"}]}},{"@e":"124","@id":"18","@s":"121","aligns":{"sc":[{"@id":"18"}],"tc":[{"@id":"16"}]}},{"@e":"132","@id":"19","@s":"125","aligns":{"sc":[{"@id":"19"}],"tc":[{"@id":"17"}]}},{"@e":"135","@id":"20","@s":"133","aligns":{"sc":[{"@id":"20"}],"tc":[{"@id":"20"}]}},{"@e":"144","@id":"21","@s":"136","aligns":{"sc":[{"@id":"21"}],"tc":[{"@id":"23"}]}},{"@e":"150","@id":"22","@s":"145","aligns":{"sc":[{"@id":"22"}],"tc":[{"@id":"21"}]}},{"@e":"166","@id":"23","@s":"151","aligns":{"sc":[{"@id":"23"}],"tc":[{"@id":"22"}]}}]},"tran":{"chars":[{"@e":"3","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"5","@id":"1","@s":"3","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"7","@id":"2","@s":"5","aligns":{"sc":[{"@id":"5"}],"tc":[{"@id":"2"}]}},{"@e":"8","@id":"3","@s":"7","aligns":{"sc":[{"@id":"4"}],"tc":[{"@id":"3"}]}},{"@e":"10","@id":"4","@s":"8","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"4"}]}},{"@e":"12","@id":"5","@s":"10","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"5"}]}},{"@e":"14","@id":"6","@s":"13","aligns":{"sc":[{"@id":"6"}],"tc":[{"@id":"6"}]}},{"@e":"15","@id":"7","@s":"14","aligns":{"sc":[{"@id":"7"}],"tc":[{"@id":"7"}]}},{"@e":"17","@id":"8","@s":"15","aligns":{"sc":[{"@id":"8"}],"tc":[{"@id":"8"}]}},{"@e":"21","@id":"9","@s":"17","aligns":{"sc":[{"@id":"9"}],"tc":[{"@id":"9"}]}},{"@e":"24","@id":"10","@s":"22","aligns":{"sc":[{"@id":"10"}],"tc":[{"@id":"10"}]}},{"@e":"26","@id":"11","@s":"24","aligns":{"sc":[{"@id":"11"}],"tc":[{"@id":"11"}]}},{"@e":"30","@id":"12","@s":"26","aligns":{"sc":[{"@id":"12"}],"tc":[{"@id":"12"}]}},{"@e":"31","@id":"13","@s":"30","aligns":{"sc":[{"@id":"13"}],"tc":[{"@id":"13"}]}},{"@e":"33","@id":"14","@s":"31","aligns":{"sc":[{"@id":"14"}],"tc":[{"@id":"14"}]}},{"@e":"34","@id":"15","@s":"33","aligns":{"sc":[{"@id":"15"}],"tc":[{"@id":"15"}]}},{"@e":"37","@id":"16","@s":"34","aligns":{"sc":[{"@id":"18"}],"tc":[{"@id":"16"}]}},{"@e":"41","@id":"17","@s":"37","aligns":{"sc":[{"@id":"19"}],"tc":[{"@id":"17"}]}},{"@e":"43","@id":"18","@s":"41","aligns":{"sc":[{"@id":"16"}],"tc":[{"@id":"18"}]}},{"@e":"45","@id":"19","@s":"43","aligns":{"sc":[{"@id":"17"}],"tc":[{"@id":"19"}]}},{"@e":"46","@id":"20","@s":"45","aligns":{"sc":[{"@id":"20"}],"tc":[{"@id":"20"}]}},{"@e":"48","@id":"21","@s":"46","aligns":{"sc":[{"@id":"22"}],"tc":[{"@id":"21"}]}},{"@e":"50","@id":"22","@s":"48","aligns":{"sc":[{"@id":"23"}],"tc":[{"@id":"22"}]}},{"@e":"53","@id":"23","@s":"51","aligns":{"sc":[{"@id":"21"}],"tc":[{"@id":"23"}]}}]}},"sentence":"They come from every corner of the globe, most not for personal glory, but for the thrill of competing or raising money for charity or honoring their fellow citizens.","sentence-eng":"They come from every corner of the globe, most not for personal glory, but for the <b>thrill<\/b> of competing or raising money for charity or honoring their fellow citizens.","sentence-speech":"They+come+from+every+corner+of+the+globe%2C+most+not+for+personal+glory%2C+but+for+the+thrill+of+competing+or+raising+money+for+charity+or+honoring+their+fellow+citizens.&le=eng","sentence-translation":"他们来自地球的每个角落，不为个人荣誉而战，而是为了激动人心的赛事或为能为慈善事业募集资金及当地市民的荣誉。","speech-size":"48kb","url":"http://www.remword.cn/article-969-1.html"}]
         */

        private String more;
        @SerializedName("sentence-count")
        private int sentencecount;
        @SerializedName("sentence-pair")
        private List<SentencepairBean> sentencepair;

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public int getSentencecount() {
            return sentencecount;
        }

        public void setSentencecount(int sentencecount) {
            this.sentencecount = sentencecount;
        }

        public List<SentencepairBean> getSentencepair() {
            return sentencepair;
        }

        public void setSentencepair(List<SentencepairBean> sentencepair) {
            this.sentencepair = sentencepair;
        }

        public static class SentencepairBean implements Serializable {
            /**
             * aligned-words : {"src":{"chars":[{"@e":"3","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]},"q":[{"@e":"17","@s":"11"}]},{"@e":"6","@id":"1","@s":"4","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"17","@id":"2","@s":"11","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]},"q":[{"@e":"17","@s":"11"}]},{"@e":"24","@id":"3","@s":"18","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"3"}]}}]},"tran":{"chars":[{"@e":"2","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"3","@id":"1","@s":"2","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"10","@id":"2","@s":"8","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]}},{"@e":"11","@id":"3","@s":"10","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"3"}]}}]}}
             * sentence : It is the thrill of the chase.
             * sentence-eng : It is the <b>thrill</b> of the chase.
             * sentence-speech : It+is+the+thrill+of+the+chase.&le=eng
             * sentence-translation : 它是一生中令人激动的时刻。
             * speech-size : 8kb
             * url : http://danci.911cha.com/it%252FThrill.html
             */

            @SerializedName("aligned-words")
            private AlignedwordsBean alignedwords;
            private String sentence;
            @SerializedName("sentence-eng")
            private String sentenceeng;
            @SerializedName("sentence-speech")
            private String sentencespeech;
            @SerializedName("sentence-translation")
            private String sentencetranslation;
            @SerializedName("speech-size")
            private String speechsize;
            private String url;

            public AlignedwordsBean getAlignedwords() {
                return alignedwords;
            }

            public void setAlignedwords(AlignedwordsBean alignedwords) {
                this.alignedwords = alignedwords;
            }

            public String getSentence() {
                return sentence;
            }

            public void setSentence(String sentence) {
                this.sentence = sentence;
            }

            public String getSentenceeng() {
                return sentenceeng;
            }

            public void setSentenceeng(String sentenceeng) {
                this.sentenceeng = sentenceeng;
            }

            public String getSentencespeech() {
                return sentencespeech;
            }

            public void setSentencespeech(String sentencespeech) {
                this.sentencespeech = sentencespeech;
            }

            public String getSentencetranslation() {
                return sentencetranslation;
            }

            public void setSentencetranslation(String sentencetranslation) {
                this.sentencetranslation = sentencetranslation;
            }

            public String getSpeechsize() {
                return speechsize;
            }

            public void setSpeechsize(String speechsize) {
                this.speechsize = speechsize;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class AlignedwordsBean implements Serializable{
                /**
                 * src : {"chars":[{"@e":"3","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]},"q":[{"@e":"17","@s":"11"}]},{"@e":"6","@id":"1","@s":"4","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"17","@id":"2","@s":"11","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]},"q":[{"@e":"17","@s":"11"}]},{"@e":"24","@id":"3","@s":"18","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"3"}]}}]}
                 * tran : {"chars":[{"@e":"2","@id":"0","@s":"1","aligns":{"sc":[{"@id":"0"}],"tc":[{"@id":"0"}]}},{"@e":"3","@id":"1","@s":"2","aligns":{"sc":[{"@id":"1"}],"tc":[{"@id":"1"}]}},{"@e":"10","@id":"2","@s":"8","aligns":{"sc":[{"@id":"2"}],"tc":[{"@id":"2"}]}},{"@e":"11","@id":"3","@s":"10","aligns":{"sc":[{"@id":"3"}],"tc":[{"@id":"3"}]}}]}
                 */

                private SrcBean src;
                private TranBean tran;

                public SrcBean getSrc() {
                    return src;
                }

                public void setSrc(SrcBean src) {
                    this.src = src;
                }

                public TranBean getTran() {
                    return tran;
                }

                public void setTran(TranBean tran) {
                    this.tran = tran;
                }

                public static class SrcBean implements Serializable{
                    private List<CharsBean> chars;

                    public List<CharsBean> getChars() {
                        return chars;
                    }

                    public void setChars(List<CharsBean> chars) {
                        this.chars = chars;
                    }

                    public static class CharsBean implements Serializable{
                        @SerializedName("@e")
                        private String _$E215; // FIXME check this code
                        @SerializedName("@id")
                        private String _$Id175; // FIXME check this code
                        @SerializedName("@s")
                        private String _$S211; // FIXME check this code
                        private AlignsBean aligns;
                        private List<QBean> q;

                        public String get_$E215() {
                            return _$E215;
                        }

                        public void set_$E215(String _$E215) {
                            this._$E215 = _$E215;
                        }

                        public String get_$Id175() {
                            return _$Id175;
                        }

                        public void set_$Id175(String _$Id175) {
                            this._$Id175 = _$Id175;
                        }

                        public String get_$S211() {
                            return _$S211;
                        }

                        public void set_$S211(String _$S211) {
                            this._$S211 = _$S211;
                        }

                        public AlignsBean getAligns() {
                            return aligns;
                        }

                        public void setAligns(AlignsBean aligns) {
                            this.aligns = aligns;
                        }

                        public List<QBean> getQ() {
                            return q;
                        }

                        public void setQ(List<QBean> q) {
                            this.q = q;
                        }

                        public static class AlignsBean implements Serializable{
                            private List<ScBean> sc;
                            private List<TcBean> tc;

                            public List<ScBean> getSc() {
                                return sc;
                            }

                            public void setSc(List<ScBean> sc) {
                                this.sc = sc;
                            }

                            public List<TcBean> getTc() {
                                return tc;
                            }

                            public void setTc(List<TcBean> tc) {
                                this.tc = tc;
                            }

                            public static class ScBean implements Serializable{
                                @SerializedName("@id")
                                private String _$Id232; // FIXME check this code

                                public String get_$Id232() {
                                    return _$Id232;
                                }

                                public void set_$Id232(String _$Id232) {
                                    this._$Id232 = _$Id232;
                                }
                            }

                            public static class TcBean implements Serializable{
                                @SerializedName("@id")
                                private String _$Id79; // FIXME check this code

                                public String get_$Id79() {
                                    return _$Id79;
                                }

                                public void set_$Id79(String _$Id79) {
                                    this._$Id79 = _$Id79;
                                }
                            }
                        }

                        public static class QBean implements Serializable{
                            @SerializedName("@e")
                            private String _$E232; // FIXME check this code
                            @SerializedName("@s")
                            private String _$S165; // FIXME check this code

                            public String get_$E232() {
                                return _$E232;
                            }

                            public void set_$E232(String _$E232) {
                                this._$E232 = _$E232;
                            }

                            public String get_$S165() {
                                return _$S165;
                            }

                            public void set_$S165(String _$S165) {
                                this._$S165 = _$S165;
                            }
                        }
                    }
                }

                public static class TranBean implements Serializable{
                    private List<CharsBeanX> chars;

                    public List<CharsBeanX> getChars() {
                        return chars;
                    }

                    public void setChars(List<CharsBeanX> chars) {
                        this.chars = chars;
                    }

                    public static class CharsBeanX implements Serializable{
                        @SerializedName("@e")
                        private String _$E281; // FIXME check this code
                        @SerializedName("@id")
                        private String _$Id22; // FIXME check this code
                        @SerializedName("@s")
                        private String _$S227; // FIXME check this code
                        private AlignsBeanX aligns;

                        public String get_$E281() {
                            return _$E281;
                        }

                        public void set_$E281(String _$E281) {
                            this._$E281 = _$E281;
                        }

                        public String get_$Id22() {
                            return _$Id22;
                        }

                        public void set_$Id22(String _$Id22) {
                            this._$Id22 = _$Id22;
                        }

                        public String get_$S227() {
                            return _$S227;
                        }

                        public void set_$S227(String _$S227) {
                            this._$S227 = _$S227;
                        }

                        public AlignsBeanX getAligns() {
                            return aligns;
                        }

                        public void setAligns(AlignsBeanX aligns) {
                            this.aligns = aligns;
                        }

                        public static class AlignsBeanX implements Serializable{
                            private List<ScBeanX> sc;
                            private List<TcBeanX> tc;

                            public List<ScBeanX> getSc() {
                                return sc;
                            }

                            public void setSc(List<ScBeanX> sc) {
                                this.sc = sc;
                            }

                            public List<TcBeanX> getTc() {
                                return tc;
                            }

                            public void setTc(List<TcBeanX> tc) {
                                this.tc = tc;
                            }

                            public static class ScBeanX implements Serializable{
                                @SerializedName("@id")
                                private String _$Id123; // FIXME check this code

                                public String get_$Id123() {
                                    return _$Id123;
                                }

                                public void set_$Id123(String _$Id123) {
                                    this._$Id123 = _$Id123;
                                }
                            }

                            public static class TcBeanX implements Serializable{
                                @SerializedName("@id")
                                private String _$Id213; // FIXME check this code

                                public String get_$Id213() {
                                    return _$Id213;
                                }

                                public void set_$Id213(String _$Id213) {
                                    this._$Id213 = _$Id213;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static class CollinsBean implements Serializable{
        private List<CollinsEntriesBean> collins_entries;

        public List<CollinsEntriesBean> getCollins_entries() {
            return collins_entries;
        }

        public void setCollins_entries(List<CollinsEntriesBean> collins_entries) {
            this.collins_entries = collins_entries;
        }

        public static class CollinsEntriesBean implements Serializable{
            /**
             * basic_entries : {"basic_entry":[{"cet":"CET4 TEM4","headword":"thrill","wordforms":{"wordform":[{"word":"thrilling"},{"word":"thrilled"},{"word":"thrills"}]}}]}
             * entries : {"entry":[{"tran_entry":[{"exam_sents":{"sent":[{"chn_sent":"我记得，在不知道圣诞节早上会收到何种礼物时自己的激动心情。","eng_sent":"I can remember the thrill of not knowing what I would get on Christmas morning."}]},"pos_entry":{"pos":"N-COUNT","pos_tips":"可数名词"},"tran":"If something gives you a <b>thrill<\/b>, it gives you a sudden feeling of great excitement, pleasure, or fear. (突然的) 激动; 狂喜; 恐惧"}]},{"tran_entry":[{"exam_sents":{"sent":[{"chn_sent":"这种紧张的气氛使他既害怕又激动。","eng_sent":"The electric atmosphere both terrified and thrilled him."}]},"pos_entry":{"pos":"V-T/V-I","pos_tips":"及物动词/不及物动词"},"tran":"If something <b>thrills<\/b> you, or if you <b>thrill<\/b> <b>at<\/b> it, it gives you a feeling of great pleasure and excitement. 使激动; 感到激动"}]},{"tran_entry":[{"seeAlsos":{"seeAlso":[{"seeword":"thrilled"},{"seeword":"thrilling"}],"seealso":"see also"}}]}]}
             * headword : thrill
             * phonetic : θrɪl
             * star : 2
             */

            private BasicEntriesBean basic_entries;
            private EntriesBean entries;
            private String headword;
            private String phonetic;
            private String star;

            public BasicEntriesBean getBasic_entries() {
                return basic_entries;
            }

            public void setBasic_entries(BasicEntriesBean basic_entries) {
                this.basic_entries = basic_entries;
            }

            public EntriesBean getEntries() {
                return entries;
            }

            public void setEntries(EntriesBean entries) {
                this.entries = entries;
            }

            public String getHeadword() {
                return headword;
            }

            public void setHeadword(String headword) {
                this.headword = headword;
            }

            public String getPhonetic() {
                return phonetic;
            }

            public void setPhonetic(String phonetic) {
                this.phonetic = phonetic;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public static class BasicEntriesBean implements Serializable {
                private List<BasicEntryBean> basic_entry;

                public List<BasicEntryBean> getBasic_entry() {
                    return basic_entry;
                }

                public void setBasic_entry(List<BasicEntryBean> basic_entry) {
                    this.basic_entry = basic_entry;
                }

                public static class BasicEntryBean implements Serializable{
                    /**
                     * cet : CET4 TEM4
                     * headword : thrill
                     * wordforms : {"wordform":[{"word":"thrilling"},{"word":"thrilled"},{"word":"thrills"}]}
                     */

                    private String cet;
                    private String headword;
                    private WordformsBean wordforms;

                    public String getCet() {
                        return cet;
                    }

                    public void setCet(String cet) {
                        this.cet = cet;
                    }

                    public String getHeadword() {
                        return headword;
                    }

                    public void setHeadword(String headword) {
                        this.headword = headword;
                    }

                    public WordformsBean getWordforms() {
                        return wordforms;
                    }

                    public void setWordforms(WordformsBean wordforms) {
                        this.wordforms = wordforms;
                    }

                    public static class WordformsBean implements Serializable{
                        private List<WordformBean> wordform;

                        public List<WordformBean> getWordform() {
                            return wordform;
                        }

                        public void setWordform(List<WordformBean> wordform) {
                            this.wordform = wordform;
                        }

                        public static class WordformBean implements Serializable{
                            /**
                             * word : thrilling
                             */

                            private String word;

                            public String getWord() {
                                return word;
                            }

                            public void setWord(String word) {
                                this.word = word;
                            }
                        }
                    }
                }
            }

            public static class EntriesBean implements Serializable{
                private List<EntryBean> entry;

                public List<EntryBean> getEntry() {
                    return entry;
                }

                public void setEntry(List<EntryBean> entry) {
                    this.entry = entry;
                }

                public static class EntryBean implements Serializable{
                    private List<TranEntryBean> tran_entry;

                    public List<TranEntryBean> getTran_entry() {
                        return tran_entry;
                    }

                    public void setTran_entry(List<TranEntryBean> tran_entry) {
                        this.tran_entry = tran_entry;
                    }

                    public static class TranEntryBean implements Serializable{
                        /**
                         * exam_sents : {"sent":[{"chn_sent":"我记得，在不知道圣诞节早上会收到何种礼物时自己的激动心情。","eng_sent":"I can remember the thrill of not knowing what I would get on Christmas morning."}]}
                         * pos_entry : {"pos":"N-COUNT","pos_tips":"可数名词"}
                         * tran : If something gives you a <b>thrill</b>, it gives you a sudden feeling of great excitement, pleasure, or fear. (突然的) 激动; 狂喜; 恐惧
                         */

                        private ExamSentsBean exam_sents;
                        private PosEntryBean pos_entry;
                        private String tran;

                        public ExamSentsBean getExam_sents() {
                            return exam_sents;
                        }

                        public void setExam_sents(ExamSentsBean exam_sents) {
                            this.exam_sents = exam_sents;
                        }

                        public PosEntryBean getPos_entry() {
                            return pos_entry;
                        }

                        public void setPos_entry(PosEntryBean pos_entry) {
                            this.pos_entry = pos_entry;
                        }

                        public String getTran() {
                            return tran;
                        }

                        public void setTran(String tran) {
                            this.tran = tran;
                        }

                        public static class ExamSentsBean implements Serializable{
                            private List<SentBean> sent;

                            public List<SentBean> getSent() {
                                return sent;
                            }

                            public void setSent(List<SentBean> sent) {
                                this.sent = sent;
                            }

                            public static class SentBean implements Serializable{
                                /**
                                 * chn_sent : 我记得，在不知道圣诞节早上会收到何种礼物时自己的激动心情。
                                 * eng_sent : I can remember the thrill of not knowing what I would get on Christmas morning.
                                 */

                                private String chn_sent;
                                private String eng_sent;

                                public String getChn_sent() {
                                    return chn_sent;
                                }

                                public void setChn_sent(String chn_sent) {
                                    this.chn_sent = chn_sent;
                                }

                                public String getEng_sent() {
                                    return eng_sent;
                                }

                                public void setEng_sent(String eng_sent) {
                                    this.eng_sent = eng_sent;
                                }
                            }
                        }

                        public static class PosEntryBean implements Serializable{
                            /**
                             * pos : N-COUNT
                             * pos_tips : 可数名词
                             */

                            private String pos;
                            private String pos_tips;

                            public String getPos() {
                                return pos;
                            }

                            public void setPos(String pos) {
                                this.pos = pos;
                            }

                            public String getPos_tips() {
                                return pos_tips;
                            }

                            public void setPos_tips(String pos_tips) {
                                this.pos_tips = pos_tips;
                            }
                        }
                    }
                }
            }
        }
    }

    public static class EcBean implements Serializable{
        /**
         * exam_type : ["高中","IELTS","初中","CET6","GRE","商务英语","考研"]
         * source : {"name":"有道词典","url":"http://dict.youdao.com"}
         * word : [{"return-phrase":{"l":{"i":"thrill"}},"trs":[{"tr":[{"l":{"i":["n. 激动；震颤；紧张"]}}]},{"tr":[{"l":{"i":["vt. 使\u2026颤动；使\u2026紧张；使\u2026感到兴奋或激动"]}}]},{"tr":[{"l":{"i":["vi. 颤抖；感到兴奋；感到紧张"]}}]}],"ukphone":"θrɪl","ukspeech":"thrill&type=1","usphone":"θrɪl","usspeech":"thrill&type=2"}]
         */

        private SourceBean source;
        private List<String> exam_type;
        private List<WordBean> word;

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public List<String> getExam_type() {
            return exam_type;
        }

        public void setExam_type(List<String> exam_type) {
            this.exam_type = exam_type;
        }

        public List<WordBean> getWord() {
            return word;
        }

        public void setWord(List<WordBean> word) {
            this.word = word;
        }

        public static class SourceBean implements Serializable{
            /**
             * name : 有道词典
             * url : http://dict.youdao.com
             */

            private String name;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class WordBean implements Serializable{
            /**
             * return-phrase : {"l":{"i":"thrill"}}
             * trs : [{"tr":[{"l":{"i":["n. 激动；震颤；紧张"]}}]},{"tr":[{"l":{"i":["vt. 使\u2026颤动；使\u2026紧张；使\u2026感到兴奋或激动"]}}]},{"tr":[{"l":{"i":["vi. 颤抖；感到兴奋；感到紧张"]}}]}]
             * ukphone : θrɪl
             * ukspeech : thrill&type=1
             * usphone : θrɪl
             * usspeech : thrill&type=2
             */

            @SerializedName("return-phrase")
            private ReturnphraseBean returnphrase;
            private String ukphone;
            private String ukspeech;
            private String usphone;
            private String usspeech;
            private List<TrsBean> trs;

            public ReturnphraseBean getReturnphrase() {
                return returnphrase;
            }

            public void setReturnphrase(ReturnphraseBean returnphrase) {
                this.returnphrase = returnphrase;
            }

            public String getUkphone() {
                return ukphone;
            }

            public void setUkphone(String ukphone) {
                this.ukphone = ukphone;
            }

            public String getUkspeech() {
                return ukspeech;
            }

            public void setUkspeech(String ukspeech) {
                this.ukspeech = ukspeech;
            }

            public String getUsphone() {
                return usphone;
            }

            public void setUsphone(String usphone) {
                this.usphone = usphone;
            }

            public String getUsspeech() {
                return usspeech;
            }

            public void setUsspeech(String usspeech) {
                this.usspeech = usspeech;
            }

            public List<TrsBean> getTrs() {
                return trs;
            }

            public void setTrs(List<TrsBean> trs) {
                this.trs = trs;
            }

            public static class ReturnphraseBean implements Serializable{
                /**
                 * l : {"i":"thrill"}
                 */

                private LBean l;

                public LBean getL() {
                    return l;
                }

                public void setL(LBean l) {
                    this.l = l;
                }

                public static class LBean implements Serializable{
                    /**
                     * i : thrill
                     */

                    private String i;

                    public String getI() {
                        return i;
                    }

                    public void setI(String i) {
                        this.i = i;
                    }
                }
            }

            public static class TrsBean implements Serializable{
                private List<TrBean> tr;

                public List<TrBean> getTr() {
                    return tr;
                }

                public void setTr(List<TrBean> tr) {
                    this.tr = tr;
                }

                public static class TrBean implements Serializable{
                    /**
                     * l : {"i":["n. 激动；震颤；紧张"]}
                     */

                    private LBeanX l;

                    public LBeanX getL() {
                        return l;
                    }

                    public void setL(LBeanX l) {
                        this.l = l;
                    }

                    public static class LBeanX implements Serializable{
                        private List<String> i;

                        public List<String> getI() {
                            return i;
                        }

                        public void setI(List<String> i) {
                            this.i = i;
                        }
                    }
                }
            }
        }
    }

    public static class MetaBean implements Serializable{
        /**
         * dicts : ["blng_sents_part","collins","meta","simple","ec"]
         * guessLanguage : eng
         * input : thrill
         * lang : eng
         * le : en
         */

        private String guessLanguage;
        private String input;
        private String lang;
        private String le;
        private List<String> dicts;

        public String getGuessLanguage() {
            return guessLanguage;
        }

        public void setGuessLanguage(String guessLanguage) {
            this.guessLanguage = guessLanguage;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getLe() {
            return le;
        }

        public void setLe(String le) {
            this.le = le;
        }

        public List<String> getDicts() {
            return dicts;
        }

        public void setDicts(List<String> dicts) {
            this.dicts = dicts;
        }
    }

    public static class SimpleBean implements Serializable{
        /**
         * query : thrill
         * word : [{"return-phrase":"thrill","ukphone":"θrɪl","ukspeech":"thrill&type=1","usphone":"θrɪl","usspeech":"thrill&type=2"}]
         */

        private String query;
        private List<WordBeanX> word;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public List<WordBeanX> getWord() {
            return word;
        }

        public void setWord(List<WordBeanX> word) {
            this.word = word;
        }

        public static class WordBeanX implements Serializable{
            /**
             * return-phrase : thrill
             * ukphone : θrɪl
             * ukspeech : thrill&type=1
             * usphone : θrɪl
             * usspeech : thrill&type=2
             */

            @SerializedName("return-phrase")
            private String returnphrase;
            private String ukphone;
            private String ukspeech;
            private String usphone;
            private String usspeech;

            public String getReturnphrase() {
                return returnphrase;
            }

            public void setReturnphrase(String returnphrase) {
                this.returnphrase = returnphrase;
            }

            public String getUkphone() {
                return ukphone;
            }

            public void setUkphone(String ukphone) {
                this.ukphone = ukphone;
            }

            public String getUkspeech() {
                return ukspeech;
            }

            public void setUkspeech(String ukspeech) {
                this.ukspeech = ukspeech;
            }

            public String getUsphone() {
                return usphone;
            }

            public void setUsphone(String usphone) {
                this.usphone = usphone;
            }

            public String getUsspeech() {
                return usspeech;
            }

            public void setUsspeech(String usspeech) {
                this.usspeech = usspeech;
            }
        }
    }
}
