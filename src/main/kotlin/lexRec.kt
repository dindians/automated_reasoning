/*
 * parsing
 * translating concrete into abstract syntax is a transformation that can be separated into two separate stages:
 * - lexical analysis (scanning) decomposes the sequences of input characters into 'tokens' (words)
 * - parsing converts the linear sequences of tokens into an abstract syntax tree
 *
 * tokenization
 * concrete syntax: e = (0 * x + 1) * 3 + 12
 * a parser turns a concrete syntax into abstract syntax (e.g. AST, abstract syntax tree)
 * a pretty-printer does the reverse
 */
tailrec fun lexRec(inp: String, tokens: MutableList<String> = mutableListOf()): MutableList<String>  {
    val (spaces, inpMinusToken) = lexWhileRec(inp = inp, prop = space)
    val (head, rest) = headAndRest(inpMinusToken)
    return if (head == null) tokens
    else {
        val prop = head.let {
            when {
                alphanumeric(it) -> alphanumeric
                symbolic(it) -> symbolic
//                    space(it) -> { _ -> false }
//                    punctuation(it) -> { _ -> false }
//                    else -> throw Exception("lexical analysis encountered invalid character '$it' after parsing '${tokens.joinToString(",")}'.")
                else -> { _ -> false }
            }
        }
        val (token, inpMinusToken) = lexWhileRec(head.toString(), rest, prop)
        lexRec(inpMinusToken, tokens.apply { add(token) })
    }
}

private tailrec fun lexWhileRec(token: String = "", inp: String, prop: (Char) -> Boolean): Pair<String,String> {
    val (head, rest) = headAndRest(inp)
    return when {
        head == null || !prop(head)-> Pair(token, inp)
        else -> lexWhileRec(token + head, rest, prop)
    }
}

private fun headAndRest(inp: String) = when {
    inp.isEmpty() -> Pair(null, inp)
    else -> Pair(inp[0], inp.drop(1))
}

private val space: (Char) -> Boolean = { " \t\n\r".matches(it) }
private val punctuation: (Char) -> Boolean = { "()[]{}".matches(it) }
private val symbolic: (Char) -> Boolean = { "~`!@#$%^&*-+=|\\:;<>.?/".matches(it) }
private val numeric: (Char) -> Boolean = { "0123456789".matches(it) }
private val alphanumeric: (Char) -> Boolean = { "abcdefghijklmnopqrstuvwxyz_'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".matches(it) }

private fun String.matches(c: Char) = contains(c)









