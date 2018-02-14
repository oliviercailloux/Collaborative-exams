package io.github.oliviercailloux.collaborative_exams.helper;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

public class AnswerAdapter  implements JsonbAdapter<Answer, JsonObject> {
    @Override
    public JsonObject adaptToJson(Answer obj) throws Exception {
        return null;
    }

    @Override
    public Answer adaptFromJson(JsonObject obj) throws Exception {

        return new Answer(obj.getString("text"),obj.getBoolean("correct"));
    }
}
