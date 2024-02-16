package org.example.player

import org.example.evaluation.Evaluator
import org.example.evaluation.FinalPositionEvaluator

abstract class EvaluationPlayer(protected val evaluator: Evaluator = FinalPositionEvaluator()) : Player