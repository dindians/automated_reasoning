/*
 * lexical analysis of a string
 * parses a tokenized string into a list of tokens
 * recursively extracts a token from the beginning of the string and add the extracted token to the beginning of the token list
 * tokens are separated by one or more separation characters
 * the set of separation characters is { ' ', '\t', '\n', '\r' }, i.e. "space", "tab", "newline", and "return"
 *
 * each iteration of this function tires to find a new token from the beginning of the string, and add this token to list of tokens found so far.
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
tailrec fun lex(inp: String, tokens: MutableList<String> = mutableListOf()): MutableList<String> {
    val inpMinusSpaces = lexWhile(inp = inp, prop = space).second
    return when {
        inpMinusSpaces.isEmpty() -> tokens
        else -> {
            val (token, inpMinusToken) = inpMinusSpaces[0].let { char -> lexWhile(char.toString(), inpMinusSpaces.drop(1), match(char)) }
            lex(inpMinusToken, tokens.apply { add(token) })
        }
    }
}

private tailrec fun lexWhile(token: String = "", inp: String, prop: (Char) -> Boolean): Pair<String,String> = when {
    inp.isEmpty() || !prop(inp[0]) -> Pair(token, inp)
    else -> lexWhile(token + inp[0], inp.drop(1), prop)
}

private fun match(value: Char) = when {
    alphanumeric(value) -> alphanumeric
    symbolic(value) -> symbolic
//                    space(it) -> { _ -> false }
//                    punctuation(it) -> { _ -> false }
//                    else -> throw Exception("lexical analysis encountered invalid character '$it' after parsing '${tokens.joinToString(",")}'.")
    else -> { _ -> false }
}

private val space: (Char) -> Boolean = { " \t\n\r".matches(it) }
private val punctuation: (Char) -> Boolean = { "()[]{}".matches(it) }
private val symbolic: (Char) -> Boolean = { "~`!@#$%^&*-+=|\\:;<>.?/".matches(it) }
internal val numeric: (Char) -> Boolean = { "0123456789".matches(it) }
private val alphanumeric: (Char) -> Boolean = { "abcdefghijklmnopqrstuvwxyz_'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".matches(it) }

private fun String.matches(c: Char) = contains(c)









